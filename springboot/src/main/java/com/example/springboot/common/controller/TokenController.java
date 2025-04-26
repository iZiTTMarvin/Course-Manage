package com.example.springboot.common.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springboot.common.annotate.JwtToken;
import com.example.springboot.common.resp.Result;
import com.example.springboot.common.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Tag(name = "校验")
@RestController
@RequestMapping("/common")
public class TokenController {

    @Operation(summary = "生成token")
    @PostMapping("/token")
    public Result<?> token(@RequestParam String acc, @RequestParam String pwd) {
        if (Objects.equals(acc, "1") && Objects.equals(pwd, "1")) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "1");
            map.put("acc", acc);
            map.put("pwd", pwd);
            String token = JWTUtils.generateToken(map);
            return Result.success("token", token);
        }
        return Result.failure("token失败");
    }

    @JwtToken
    @Operation(summary = "测试token")
    @PostMapping("/test")
    public Result<?> test(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        DecodedJWT decodedJWT = JWTUtils.resolveToken(token);
        //
        Date expiresAt = decodedJWT.getExpiresAt();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeLag = expiresAt.getTime() - new Date().getTime();
        HashMap<String, String> map = new HashMap<>();
        map.put("Token生成时间", sdf.format(expiresAt));
        long day = timeLag / (24 * 60 * 60 * 1000); //天
        long hour = (timeLag / (60 * 60 * 1000) - day * 24); //小时
        long minute = ((timeLag / (60 * 1000)) - day * 24 * 60 - hour * 60); //分钟
        long s = (timeLag / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60); //1秒 = 1000毫秒
        map.put("Token剩余时间", day + "天 " + hour + "时 " + minute + "分 " + s + "秒");
        //

        String acc = decodedJWT.getClaim("acc").asString();
        map.put("账号", acc);
        return Result.success("JWT 测试接口", map);
    }

}
