package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.UserEntity;
import com.example.springboot.system.mapper.UserMapper;
import com.example.springboot.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */

@Tag(name = "用户")
@RestController
@RequestMapping("/userEntity")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageUser")
    public Result<?> listPageUser(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<UserEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = userMapper.selectPage(rowPage, wrapper);
        return Result.success("数据请求成功", rowPage);
    }

    @JwtToken
    @Operation(summary = "查询编号")
    @PostMapping("/findByIDUser/{param}")
    public Result<?> findByIDUser(@PathVariable @Validated String param) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.like(("user_id"), param);
        wrapper.eq(("undock"), 0);
        if (userService.count(wrapper) == 0) return Result.success("查询成功，但用户不存在");
        return Result.success("查询成功", userMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "查询名称")
    @PostMapping("/findByNameUser/{param}")
    public Result<?> findByNameUser(@PathVariable @Validated String param) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.like(("user_name"), param);
        wrapper.eq(("undock"), 0);
        if (userService.count(wrapper) == 0) return Result.failure("查询成功，但用户不存在");
        return Result.success("查询成功", userMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "查询账号")
    @PostMapping("/findByAccUser/{param}")
    public Result<?> findByAccUser(@PathVariable @Validated String param) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_acc"), param);
        wrapper.eq(("undock"), 0);
        if (userService.count(wrapper) == 0) return Result.failure("查询成功，但用户不存在");
        return Result.success("查询成功", userMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findUser")
    public Result<?> findUser(@RequestBody Map<String, Object> params) {
        // 兼容处理start和startYear字段
        if (params.get("start") != null && !params.get("start").toString().isEmpty() && 
            (params.get("startYear") == null || params.get("startYear").toString().isEmpty())) {
            params.put("startYear", params.get("start"));
        }
        
        // 处理state字段
        if (params.get("state") == null) {
            params.put("state", 1); // 默认查询状态为1的记录
        }
        
        // 使用新添加的findUserByCondition方法进行查询
        List<UserEntity> users = userMapper.findUserByCondition(params);
        
        if (users.isEmpty()) {
            return Result.failure("查询成功，但用户不存在");
        }
        
        return Result.success("查询成功", users);
    }

    @JwtToken
    @Operation(summary = "保存用户")
    @PostMapping("/insert")
    public Result<?> insertUser(@RequestBody @Validated UserEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_id"), entity.getId());
        if (userService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        
        // 检查角色值，如果为0则设置为默认值1（学生）
        if (entity.getRole() == 0) {
            entity.setRole(1);
        }
        
        // 处理学院、专业和班级字段，如果为空则设置为null
        if (entity.getAcademic() != null && entity.getAcademic().isEmpty()) {
            entity.setAcademic(null);
        }
        if (entity.getProfess() != null && entity.getProfess().isEmpty()) {
            entity.setProfess(null);
        }
        if (entity.getTeam() != null && entity.getTeam().isEmpty()) {
            entity.setTeam(null);
        }
        
        // 确保入学年份字段不为空
        if (entity.getStartYear() == null || entity.getStartYear().isEmpty()) {
            entity.setStartYear(new SimpleDateFormat("yyyy").format(new Date()));
        }
        if (userService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteUser(@PathVariable @Validated String param) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_id"), param);
        if (userService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (userService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempUser(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_id"), param);
        if (userService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        UserEntity user = userService.getOne(wrapper);
        user.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        user.setUpdateBy(name);
        user.setUpdateTime(new Date());
        if (userService.updateById(user)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListUser() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(userMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverUser(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_id"), param);
        if (userService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        UserEntity user = userService.getOne(wrapper);
        user.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        user.setUpdateBy(name);
        user.setUpdateTime(new Date());
        if (userService.updateById(user)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateUser(@RequestBody @Validated UserEntity entity) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("user_id"), entity.getId());
        if (userService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (userService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelUser(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<UserEntity> list = userService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelUser(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<UserEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new UserListener())
                    .head(UserEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (UserEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                userService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
