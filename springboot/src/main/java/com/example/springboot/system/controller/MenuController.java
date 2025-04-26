package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.MenuEntity;
import com.example.springboot.system.mapper.MenuMapper;
import com.example.springboot.system.service.MenuService;
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
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据表 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */

@Tag(name = "菜单")
@RestController
@RequestMapping("/menuEntity")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageMenu")
    public Result<?> listPageMenu(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<MenuEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = menuMapper.selectPage(rowPage, wrapper);
        return Result.success("数据请求成功", rowPage);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findMenu")
    public Result<?> findMenu(@RequestBody MenuEntity entity) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        if (entity.getName() != "") wrapper.like(("menu_name"), entity.getName());
        if (entity.getIcon() != "") wrapper.like(("menu_icon"), entity.getIcon());
        if (entity.getKeypath() != "") wrapper.like(("menu_keypath"), entity.getKeypath());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (menuService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", menuMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertMenu(@RequestBody @Validated MenuEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("menu_id"), entity.getId());
        if (menuService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (menuService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteMenu(@PathVariable @Validated String param) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("menu_id"), param);
        if (menuService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (menuService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempMenu(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("menu_id"), param);
        if (menuService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        MenuEntity menu = menuService.getOne(wrapper);
        menu.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        menu.setUpdateBy(name);
        menu.setUpdateTime(new Date());
        if (menuService.updateById(menu)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListMenu() {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(menuMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverMenu(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("menu_id"), param);
        if (menuService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        MenuEntity menu = menuService.getOne(wrapper);
        menu.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        menu.setUpdateBy(name);
        menu.setUpdateTime(new Date());
        if (menuService.updateById(menu)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateMenu(@RequestBody @Validated MenuEntity entity) {
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("menu_id"), entity.getId());
        if (menuService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (menuService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelMenu(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<MenuEntity> list = menuService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), MenuEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelMenu(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<MenuEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new MenuListener())
                    .head(MenuEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (MenuEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                menuService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
