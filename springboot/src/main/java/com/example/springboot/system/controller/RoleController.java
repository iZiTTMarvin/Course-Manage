package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.MenuEntity;
import com.example.springboot.system.entity.RmEntity;
import com.example.springboot.system.entity.RoleEntity;
import com.example.springboot.system.entity.UserEntity;
import com.example.springboot.system.mapper.RmMapper;
import com.example.springboot.system.mapper.RoleMapper;
import com.example.springboot.system.resp.RmResp;
import com.example.springboot.system.resp.RoleResp;
import com.example.springboot.system.service.MenuService;
import com.example.springboot.system.service.RoleService;
import com.example.springboot.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据表 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */

@Tag(name = "角色")
@RestController
@RequestMapping("/roleEntity")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RmMapper rmMapper;

    @Autowired
    private MenuService menuService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageRole")
    public Result<?> listPageRole(@RequestParam Integer page, @RequestParam Integer pageSize) {
        Page<RoleEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = roleMapper.selectPage(rowPage, wrapper);
        //开辟空间
        List<RoleEntity> records = rowPage.getRecords();
        Page<RoleResp> rowPageP = new Page(page, pageSize);
        ArrayList<RoleResp> roleRecords = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            RoleResp roleResp = new RoleResp();
            BeanUtils.copyProperties(records.get(i), roleResp);
            //获取人数
            Integer id = records.get(i).getId();
            QueryWrapper<UserEntity> wrapperU = new QueryWrapper<>();
            wrapperU.eq("user_role", id);
            long count = userService.count(wrapperU);
            roleResp.setCount(count);
            //获取角色-菜单
            QueryWrapper<RmEntity> wrapperRM = new QueryWrapper<>();
            wrapperRM.eq("rm_role", id);
            List<RmEntity> listRM = rmMapper.selectList(wrapperRM);
            //rolePEntity.setRm(listRM);

            //开辟空间
            ArrayList<RmResp> listRMP = new ArrayList<>();
            for (int j = 0; j < listRM.size(); j++) {
                RmEntity rm = listRM.get(j);
                RmResp rmP = new RmResp();
                BeanUtils.copyProperties(rm, rmP);
                listRMP.add(rmP);
            }
            //查找父级和名称
            for (int j = 0; j < listRMP.size(); j++) {
                RmResp rmPEntity = listRMP.get(j);
                QueryWrapper<MenuEntity> wrapperM = new QueryWrapper<>();
                wrapperM.eq("menu_id", rmPEntity.getMenu());
                MenuEntity entity = menuService.getOne(wrapperM);
                rmPEntity.setName(entity.getName());
                rmPEntity.setParent(entity.getParent());
            }
            //模拟深拷贝
            ArrayList<RmResp> listCopy = new ArrayList<>();
            for (int j = 0; j < listRMP.size(); j++) {
                RmResp rmP = listRMP.get(j);
                RmResp rmPCopy = new RmResp();
                BeanUtils.copyProperties(rmP, rmPCopy);
                listCopy.add(rmPCopy);
            }
            //分离选中项
            ArrayList<Integer> listC = new ArrayList<>();
            for (int j = 0; j < listCopy.size(); j++) {
                RmResp rmPEntity = listCopy.get(j);
                if (rmPEntity.getUndock() == 0) {
                    listC.add(rmPEntity.getId());
                }
            }
            roleResp.setRmc(listC);
            //分离父子
            ArrayList<RmResp> listP = new ArrayList<>();
            ArrayList<RmResp> listS = new ArrayList<>();
            for (int j = 0; j < listRMP.size(); j++) {
                RmResp rmP = listRMP.get(j);
                if (rmP.getParent() == 0) {
                    listP.add(rmP);
                } else {
                    listS.add(rmP);
                }
            }
            //放入
            for (int j = 0; j < listP.size(); j++) {
                Integer idp = listP.get(j).getMenu();
                ArrayList<RmResp> children = new ArrayList<>();
                for (int k = 0; k < listS.size(); k++) {
                    Integer parent = listS.get(k).getParent();
                    if (parent == idp) {
                        children.add(listS.get(k));
                    }
                }
                listP.get(j).setChildren(children);
            }
            roleResp.setRmp(listP);
            //添加到records
            roleRecords.add(roleResp);
        }
        rowPageP.setRecords(roleRecords);
        return Result.success("数据请求成功", rowPageP);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findRole")
    public Result<?> findRole(@RequestBody RoleEntity entity) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        if (entity.getName() != "") wrapper.like(("role_name"), entity.getName());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (roleService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", roleMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertRole(@RequestBody @Validated RoleEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("role_id"), entity.getId());
        if (roleService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (roleService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteRole(@PathVariable @Validated String param) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("role_id"), param);
        if (roleService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (roleService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempRole(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("role_id"), param);
        if (roleService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        RoleEntity role = roleService.getOne(wrapper);
        role.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        role.setUpdateBy(name);
        role.setUpdateTime(new Date());
        if (roleService.updateById(role)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListRole() {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(roleMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverRole(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("role_id"), param);
        if (roleService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        RoleEntity role = roleService.getOne(wrapper);
        role.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        role.setUpdateBy(name);
        role.setUpdateTime(new Date());
        if (roleService.updateById(role)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateRole(@RequestBody @Validated RoleEntity entity) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("role_id"), entity.getId());
        if (roleService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (roleService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelRole(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<RoleEntity> list = roleService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), RoleEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelRole(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<RoleEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new RoleListener())
                    .head(RoleEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (RoleEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                roleService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
