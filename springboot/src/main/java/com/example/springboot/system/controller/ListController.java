package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.ListEntity;
import com.example.springboot.system.entity.ProfessEntity;
import com.example.springboot.system.entity.TypeEntity;
import com.example.springboot.system.mapper.ListMapper;
import com.example.springboot.system.resp.ListResp;
import com.example.springboot.system.service.ListService;
import com.example.springboot.system.service.ProfessService;
import com.example.springboot.system.service.TypeService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@Tag(name = "课程目录")
@RestController
@RequestMapping("/listEntity")
public class ListController {

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private ListService listService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ProfessService professService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageList")
    public Result<?> listPageList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<ListEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = listMapper.selectPage(rowPage, wrapper);

        Page<ListResp> rowPageP = new Page(page, pageSize);
        ArrayList<ListResp> listP = new ArrayList<>();
        List<ListEntity> records = rowPage.getRecords();
        for (int i = 0; i < records.size(); i++) {
            ListEntity listEntity = records.get(i);
            ListResp listResp = new ListResp();
            BeanUtils.copyProperties(listEntity, listResp);
            listP.add(listResp);
        }
        for (int i = 0; i < listP.size(); i++) {
            ListResp listResp = listP.get(i);
            Integer type = listResp.getType();
            TypeEntity entityT = typeService.getById(type);
            listResp.setTname(entityT.getName());
            if (listResp.getProfess() != null) {
                Integer profess = listResp.getProfess();
                ProfessEntity entityP = professService.getById(profess);
                listResp.setPname(entityP.getName());
            }
        }
        rowPageP.setRecords(listP);
        return Result.success("数据请求成功", rowPageP);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findList")
    public Result<?> findList(@RequestBody ListEntity entity) {
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        if (entity.getName() != "") wrapper.like(("list_name"), entity.getName());
        if (entity.getGrade() != "") wrapper.like(("list_grade"), entity.getGrade());
        if (entity.getType() == null) entity.setType(0);
        if (entity.getType() != 0) wrapper.like(("list_type"), entity.getType());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (listService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", listMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertList(@RequestBody @Validated ListEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("list_id"), entity.getId());
        if (listService.count(wrapper) != 0) return Result.failure("保存失败，数据已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (listService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteList(@PathVariable @Validated String param) {
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("list_id"), param);
        if (listService.count(wrapper) == 0) return Result.failure("删除失败，数据不存在");
        if (listService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempList(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("list_id"), param);
        if (listService.count(wrapper) == 0) return Result.failure("删除失败，数据不存在");
        ListEntity list = listService.getOne(wrapper);
        list.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        list.setUpdateBy(name);
        list.setUpdateTime(new Date());
        if (listService.updateById(list)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListList() {
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(listMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverList(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("list_id"), param);
        if (listService.count(wrapper) == 0) return Result.failure("恢复失败，数据不存在");
        ListEntity list = listService.getOne(wrapper);
        list.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        list.setUpdateBy(name);
        list.setUpdateTime(new Date());
        if (listService.updateById(list)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public Result<?> updateList(@RequestBody @Validated ListEntity entity) {
        QueryWrapper<ListEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("list_id"), entity.getId());
        if (listService.count(wrapper) == 0) return Result.failure("修改失败，数据不存在");
        if (listService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelList(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<ListEntity> list = listService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ListEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelList(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<ListEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new ListListener())
                    .head(ListEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页数据，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (ListEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                listService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
