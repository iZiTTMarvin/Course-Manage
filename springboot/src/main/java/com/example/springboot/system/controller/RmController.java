package com.example.springboot.system.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import com.example.springboot.system.entity.RmEntity;
import com.example.springboot.system.mapper.RmMapper;
import com.example.springboot.system.service.RmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 关联表 前端控制器
 * </p>
 *
 * @author he
 * @since 2024-05-21
 */

@Tag(name = "权限分配")
@RestController
@RequestMapping("/rmEntity")
public class RmController {

    @Autowired
    private RmService rmService;

    @Autowired
    private RmMapper rmMapper;

    @JwtToken
    @Operation(summary = "暂时删除")
    @PostMapping("/updateRm")
    public Result<?> updateRm(@RequestParam @Validated Integer role, @RequestParam @Validated Integer menu, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<RmEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("rm_role"), role).eq("rm_menu", menu);
        if (rmService.count(wrapper) == 0) return Result.failure("删除失败，编号不存在");
        RmEntity rm = rmService.getOne(wrapper);
        rm.setUndock(1);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        rm.setUpdateBy(name);
        rm.setUpdateTime(new Date());
        if (rmService.updateById(rm)) return Result.success("删除成功");
        return Result.failure("系统超时，删除失败");
    }

    @JwtToken
    @Operation(summary = "恢复数据")
    @PostMapping("/recoverRm")
    public Result<?> recoverRm(@RequestParam @Validated Integer role, @RequestParam @Validated Integer menu, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //修改
        QueryWrapper<RmEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(("rm_role"), role).eq("rm_menu", menu);
        if (rmService.count(wrapper) == 0) return Result.failure("恢复失败，编号不存在");
        RmEntity rm = rmService.getOne(wrapper);
        rm.setUndock(0);
        //设置修改者
        String name = decodedJWT.getClaim("name").asString();
        rm.setUpdateBy(name);
        rm.setUpdateTime(new Date());
        if (rmService.updateById(rm)) return Result.success("数据恢复成功");
        return Result.failure("系统超时，数据恢复失败");
    }


}
