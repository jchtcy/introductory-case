package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.dto.LoginBody;

import java.util.Map;

public interface IAuthService {
    /**
     * 登录
     * @param loginBody
     * @return
     */
    Map<String, Object> login(LoginBody loginBody);
    /**
     * 登出
     * @param token
     * @return
     */
    void logout(String token);
}
