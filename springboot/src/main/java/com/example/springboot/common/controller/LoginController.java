package com.example.springboot.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.MenuEntity;
import com.example.springboot.system.entity.RmEntity;
import com.example.springboot.system.entity.RoleEntity;
import com.example.springboot.system.entity.UserEntity;
import com.example.springboot.system.mapper.MenuMapper;
import com.example.springboot.system.mapper.RmMapper;
import com.example.springboot.system.resp.MenuResp;
import com.example.springboot.system.service.MenuService;
import com.example.springboot.system.service.RoleService;
import com.example.springboot.system.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "登录")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RmMapper rmMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuMapper menuMapper;

    @PostMapping("/login")
    public Result<?> login(@RequestParam String acc, @RequestParam String pwd, @RequestParam String code, HttpSession session) {
        //验证码比对
//        if (!session.getAttribute("code").equals(code)) {
//            return Result.failure("验证码错误");
//        }
        //获取个人信息
        QueryWrapper<UserEntity> wrapperUser = new QueryWrapper<>();
        wrapperUser.eq("user_acc", acc);
        UserEntity user = userService.getOne(wrapperUser);
        //账号存在性
        if (user == null) return Result.failure("账号不存在，登陆失败");
        //账号是否正常
        if (user.getState() == 0 || user.getUndock() == 1) return Result.failure("账号状态异常，请联系管理员处理");
        //密码比对
        if (!pwd.equals(user.getPwd())) return Result.failure("密码错误，登陆失败");
        //生成token
        HashMap<String, String> map = new HashMap<>();
        map.put("name", user.getName());
        map.put("acc", user.getAcc());
        map.put("pwd", user.getPwd());
        String token = JWTUtils.generateToken(map);
        //获取角色
        QueryWrapper<RoleEntity> wrapperRole = new QueryWrapper<>();
        wrapperRole.eq("role_id", user.getRole());
        RoleEntity role = roleService.getOne(wrapperRole);
        if (role.getUndock() == 1) return Result.failure("登陆失败，用户角色存在异常");
        //获取角色-菜单
        QueryWrapper<RmEntity> wrapperRm = new QueryWrapper<>();
        wrapperRm.eq("rm_role", role.getId()).eq("undock", 0);
        List<RmEntity> listRm = rmMapper.selectList(wrapperRm);
        //获取全部菜单
        ArrayList<MenuEntity> listM = new ArrayList<>();
        for (RmEntity rm : listRm) {
            QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("menu_id", rm.getMenu()).eq("undock", 0);
            MenuEntity menu = menuMapper.selectOne(wrapper);
            if (menu != null) listM.add(menu);
        }
        //分离父子及开辟空间
        ArrayList<MenuResp> listP = new ArrayList<>();
        ArrayList<MenuEntity> listS = new ArrayList<>();
        for (int i = 0; i < listM.size(); i++) {
            MenuEntity menu = listM.get(i);
            int parent = listM.get(i).getParent();
            if (parent == 0) {
                MenuResp menuP = new MenuResp();
                BeanUtils.copyProperties(menu, menuP);
                listP.add(menuP);
            } else {
                listS.add(menu);
            }
        }
        //将子集放入父级
        for (int i = 0; i < listP.size(); i++) {
            MenuResp menuP = listP.get(i);
            Integer id = menuP.getId();
            ArrayList<MenuEntity> children = new ArrayList<>();
            for (int j = 0; j < listS.size(); j++) {
                MenuEntity menu = listS.get(j);
                int parent = menu.getParent();
                if (id == parent) {
                    children.add(menu);
                }
            }
            // 使用 Stream API 按 ranks 字段排序
            List<MenuEntity> childrenR = children.stream()
                    .sorted((item1, item2) -> Integer.compare(item1.getRanks(), item2.getRanks())).toList();
            menuP.setChildren(childrenR);
        }
        //若子集的父级不存在，则升级为父级
        ArrayList<Integer> integersP = new ArrayList<>();
        for (int i = 0; i < listP.size(); i++) {
            Integer id = listP.get(i).getId();
            integersP.add(id);
        }
        ArrayList<Integer> integersS = new ArrayList<>();
        for (int i = 0; i < listS.size(); i++) {
            Integer id = listS.get(i).getParent();
            integersS.add(id);
        }
        // 将列表转换为集合
        Set<Integer> setP = integersP.stream().collect(Collectors.toSet());
        Set<Integer> setS = integersS.stream().collect(Collectors.toSet());
        // 找出在 integersP 中但不在 integersS 中的元素
        Set<Integer> diffInListP = setP.stream()
                .filter(e -> !setS.contains(e))
                .collect(Collectors.toSet());
        // 找出在 integersS 中但不在 integersP 中的元素
        Set<Integer> diffInListS = setS.stream()
                .filter(e -> !setP.contains(e))
                .collect(Collectors.toSet());
        List<Integer> ds = diffInListS.stream().collect(Collectors.toList());
        for (int i = 0; i < ds.size(); i++) {
            for (int j = 0; j < listS.size(); j++) {
                if (ds.get(i) == listS.get(j).getParent()) {
                    MenuEntity menuEntity = listS.get(j);
                    MenuResp menuResp = new MenuResp();
                    BeanUtils.copyProperties(menuEntity, menuResp);
                    listP.add(menuResp);
                }
            }
        }

        //返回数据
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);
        hashMap.put("user", user);
        hashMap.put("role", role);
        hashMap.put("menu", listP);
        return Result.success("登录成功", hashMap);
    }
}
