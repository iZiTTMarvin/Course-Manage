package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.*;
import com.example.springboot.system.resp.TableResp;
import com.example.springboot.system.mapper.TableMapper;
import com.example.springboot.system.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.buf.UEncoder;
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
 * 课表 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-20
 */

@Tag(name = "课程开设")
@RestController
@RequestMapping("/tableEntity")
public class TableController {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private TableService tableService;

    @Autowired
    private TimeService timeService;

    @Autowired
    private ListService listService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private UserService userService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageTable")
    public Result<?> listPageTable(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<TableEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = tableMapper.selectPage(rowPage, wrapper);

        Page<TableResp> rowPageP = new Page(page, pageSize);
        ArrayList<TableResp> listP = new ArrayList<>();

        for (int i = 0; i < rowPage.getRecords().size(); i++) {
            TableEntity table = rowPage.getRecords().get(i);
            TableResp tableResp = new TableResp();
            BeanUtils.copyProperties(table, tableResp);
            listP.add(tableResp);
        }

        for (int i = 0; i < listP.size(); i++) {
            TableResp tableResp = listP.get(i);
            Integer time = tableResp.getTime();
            if (time != null) {
                TimeEntity timeEntity = timeService.getById(time);
                tableResp.setTname(timeEntity.getName());
            }
            Integer list = tableResp.getList();
            if (list != null) {
                ListEntity listEntity = listService.getById(list);
                tableResp.setLname(listEntity.getName());
            }
            Integer classroom = tableResp.getClassroom();
            if (classroom != null) {
                ClassroomEntity classroomEntity = classroomService.getById(classroom);
                tableResp.setCname(classroomEntity.getName() + "-" + classroomEntity.getRoom());
            }
            Integer user = tableResp.getTeacher();
            if (user != null) {
                UserEntity userEntity = userService.getById(user);
                tableResp.setUname(userEntity.getName());
            }
        }
        rowPageP.setRecords(listP);
        return Result.success("数据请求成功", rowPageP);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findTable")
    public Result<?> findTable(@RequestBody TableEntity entity) {
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        if (entity.getTime() != null) wrapper.like(("table_time"), entity.getTime());
        if (entity.getWeek() != null) wrapper.like(("table_week"), entity.getWeek());
        if (entity.getList() != null) wrapper.like(("table_list"), entity.getList());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (tableService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", tableMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertTable(@RequestBody @Validated TableEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("table_id"), entity.getId());
        if (tableService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (tableService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteTable(@PathVariable @Validated String param) {
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("table_id"), param);
        if (tableService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (tableService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempTable(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("table_id"), param);
        if (tableService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        TableEntity table = tableService.getOne(wrapper);
        table.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        table.setUpdateBy(name);
        table.setUpdateTime(new Date());
        if (tableService.updateById(table)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListTable() {
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(tableMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverTable(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("table_id"), param);
        if (tableService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        TableEntity table = tableService.getOne(wrapper);
        table.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        table.setUpdateBy(name);
        table.setUpdateTime(new Date());
        if (tableService.updateById(table)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateTable(@RequestBody @Validated TableEntity entity) {
        QueryWrapper<TableEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("table_id"), entity.getId());
        if (tableService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (tableService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelTable(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<TableEntity> list = tableService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TableEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelTable(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<TableEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new TableListener())
                    .head(TableEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (TableEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                tableService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
