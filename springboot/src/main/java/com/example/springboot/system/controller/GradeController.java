package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.GradeEntity;
import com.example.springboot.system.mapper.GradeMapper;
import com.example.springboot.system.service.GradeService;
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
 * 学生成绩 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */

@Tag(name = "成绩")
@RestController
@RequestMapping("/gradeEntity")
public class GradeController {


    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private GradeService gradeService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageGrade")
    public Result<?> listPageGrade(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<GradeEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = gradeMapper.selectPage(rowPage, wrapper);
        return Result.success("数据请求成功", rowPage);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findGrade")
    public Result<?> findGrade(@RequestBody GradeEntity entity) {
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        if (entity.getUser() != 0) wrapper.like(("grade_user"), entity.getUser());
        if (entity.getList() != 0) wrapper.like(("grade_list"), entity.getList());
        if (entity.getScore() != 0) wrapper.like(("grade_score"), entity.getScore());
        if (entity.getGrade() != 0) wrapper.like(("grade_grade"), entity.getGrade());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (gradeService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", gradeMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertGrade(@RequestBody @Validated GradeEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("grade_id"), entity.getId());
        if (gradeService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (gradeService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteGrade(@PathVariable @Validated String param) {
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("grade_id"), param);
        if (gradeService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (gradeService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempGrade(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("grade_id"), param);
        if (gradeService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        GradeEntity grade = gradeService.getOne(wrapper);
        grade.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        grade.setUpdateBy(name);
        grade.setUpdateTime(new Date());
        if (gradeService.updateById(grade)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListGrade() {
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(gradeMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverGrade(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("grade_id"), param);
        if (gradeService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        GradeEntity grade = gradeService.getOne(wrapper);
        grade.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        grade.setUpdateBy(name);
        grade.setUpdateTime(new Date());
        if (gradeService.updateById(grade)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateGrade(@RequestBody @Validated GradeEntity entity) {
        QueryWrapper<GradeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("grade_id"), entity.getId());
        if (gradeService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (gradeService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelGrade(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<GradeEntity> list = gradeService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), GradeEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelGrade(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<GradeEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new GradeListener())
                    .head(GradeEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (GradeEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                gradeService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
