package com.example.springboot.system.controller;

import com.alibaba.excel.EasyExcel;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.ProfessEntity;
import com.example.springboot.system.entity.TeamEntity;
import com.example.springboot.system.mapper.TeamMapper;
import com.example.springboot.system.resp.TeamResp;
import com.example.springboot.system.service.ProfessService;
import com.example.springboot.system.service.TeamService;
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
 * 班级 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-22
 */

@Tag(name = "班级")
@RestController
@RequestMapping("/teamEntity")
public class TeamController {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ProfessService professService;

    @JwtToken
    @Operation(summary = "列表分页")
    @PostMapping("/listPageTeam")
    public Result<?> listPageTeam(@RequestParam Integer page, @RequestParam Integer pageSize) {
        //分页参数
        Page<TeamEntity> rowPage = new Page(page, pageSize);
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 0);
        rowPage = teamMapper.selectPage(rowPage, wrapper);

        Page<TeamResp> rowPageP = new Page(page, pageSize);
        ArrayList<TeamResp> listP = new ArrayList<>();
        List<TeamEntity> records = rowPage.getRecords();
        for (int i = 0; i < records.size(); i++) {
            TeamEntity team = records.get(i);
            TeamResp teamResp = new TeamResp();
            BeanUtils.copyProperties(team, teamResp);
            listP.add(teamResp);
        }
        for (int i = 0; i < listP.size(); i++) {
            TeamResp teamResp = listP.get(i);
            Integer profess = teamResp.getProfess();
            ProfessEntity professEntity = professService.getById(profess);
            teamResp.setPname(professEntity.getName());
        }
        rowPageP.setRecords(listP);
        return Result.success("数据请求成功", rowPageP);
    }

    @JwtToken
    @Operation(summary = "条件查询")
    @PostMapping("/findTeam")
    public Result<?> findTeam(@RequestBody TeamEntity entity) {
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        if (entity.getName() != "") wrapper.like(("team_name"), entity.getName());
        wrapper.like(("state"), entity.getState());
        wrapper.eq(("undock"), 0);
        if (teamService.count(wrapper) == 0) return Result.success("查询成功，但数据不存在");
        return Result.success("查询成功", teamMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "保存数据")
    @PostMapping("/insert")
    public Result<?> insertTeam(@RequestBody @Validated TeamEntity entity, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //保存
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("team_id"), entity.getId());
        if (teamService.count(wrapper) != 0) return Result.failure("保存失败，编号已存在");
        //设置创建者
        String name = decodedJWT.getClaim("name").asString();
        entity.setCreateBy(name);
        entity.setCreateTime(new Date());
        if (teamService.save(entity)) return Result.success("保存成功");
        return Result.failure("系统超时，保存失败");
    }

    @JwtToken
    @Operation(summary = "永久删除")
    @PostMapping("/delete/{param}")
    public Result<?> deleteTeam(@PathVariable @Validated String param) {
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("team_id"), param);
        if (teamService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        if (teamService.remove(wrapper)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/deleteTemp/{param}")
    public Result<?> deleteTempTeam(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("team_id"), param);
        if (teamService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        TeamEntity team = teamService.getOne(wrapper);
        team.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        team.setUpdateBy(name);
        team.setUpdateTime(new Date());
        if (teamService.updateById(team)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复列表")
    @PostMapping("/recoverList")
    public Result<?> recoverListTeam() {
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("undock", 1);
        return Result.success(teamMapper.selectList(wrapper));
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recover/{param}")
    public Result<?> recoverTeam(@PathVariable @Validated String param, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("team_id"), param);
        if (teamService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        TeamEntity team = teamService.getOne(wrapper);
        team.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        team.setUpdateBy(name);
        team.setUpdateTime(new Date());
        if (teamService.updateById(team)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }

    @JwtToken
    @Operation(summary = "修改编号")
    @PostMapping("/update")
    public Result<?> updateTeam(@RequestBody @Validated TeamEntity entity) {
        QueryWrapper<TeamEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("team_id"), entity.getId());
        if (teamService.count(wrapper) == 0) return Result.failure("修改失败，编号不存在");
        if (teamService.updateById(entity)) return Result.success("修改成功");
        return Result.failure("系统超时，修改失败");
    }

    @Operation(summary = "导出数据")
    @PostMapping("exportExcel")
    public void exportExcelTeam(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("Excel数据表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        List<TeamEntity> list = teamService.list();
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TeamEntity.class).sheet("Excel数据表").doWrite(list);
    }

    @JwtToken
    @Operation(summary = "导入数据")
    @PostMapping("/importExcel")
    public Result<?> importExcelTeam(MultipartFile file, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        try {
            //获取文件的输入流
            InputStream inputStream = file.getInputStream();
            List<TeamEntity> list = EasyExcel.read(inputStream) //调用read方法
                    //注册自定义监听器，字段校验可以在监听器内实现
                    //.registerReadListener(new TeamListener())
                    .head(TeamEntity.class) //对应导入的实体类
                    .sheet(0) //导入数据的sheet页编号，0代表第一个sheet页，如果不填，则会导入所有sheet页的数据
                    .headRowNumber(1) //列表头行数，1代表列表头有1行，第二行开始为数据行
                    .doReadSync();//开始读Excel，返回一个List<T>集合，继续后续入库操作
            //模拟导入数据库操作
            String name = decodedJWT.getClaim("name").asString();
            for (TeamEntity entity : list) {
                entity.setCreateBy(name);
                entity.setCreateTime(new Date());
                teamService.saveOrUpdate(entity);
            }
            return Result.success("导入成功");
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
