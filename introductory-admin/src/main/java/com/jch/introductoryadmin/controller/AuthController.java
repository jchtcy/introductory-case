package com.jch.introductoryadmin.controller;

import com.alibaba.fastjson2.JSONObject;
import com.jch.introductoryadmin.service.IAuthService;
import com.jch.introductoryadmin.dto.LoginBody;
import com.jch.introductorycommom.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = {"登录接口"})
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService tokenService;

    /**
     * 登录
     * @param loginBody
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResultUtil<Map<String, Object>> login(@RequestBody LoginBody loginBody){

        Map<String, Object> login = tokenService.login(loginBody);
        if (login.size() == 0) {
            return ResultUtil.fail("300","用户名或密码错误");
        }
        return ResultUtil.ok(login);
    }


    /**
     * 登出
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public ResultUtil<?> logout(@RequestHeader("Token") String token) {
        tokenService.logout(token);
        return ResultUtil.ok();
    }
}
