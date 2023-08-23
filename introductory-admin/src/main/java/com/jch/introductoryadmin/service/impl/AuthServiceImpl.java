package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.domain.User;
import com.jch.introductoryadmin.service.IAuthService;
import com.jch.introductoryadmin.service.IUserService;
import com.jch.introductoryadmin.dto.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("tokenService")
public class AuthServiceImpl implements IAuthService{
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 登录
     * @param loginBody
     * @return token
     */
    @Override
    public Map<String, Object> login(LoginBody loginBody) {
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        Map<String, Object> data = new HashMap<>();
        User loginUser = userService.selectByUsername(username);
        if (loginUser != null && passwordEncoder.matches(password, loginUser.getPassword())) {
            //todo 暂时用UUID
            String token = "user:" + UUID.randomUUID();
            //存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(token, loginUser,30, TimeUnit.MINUTES);
            //返回数据
            data.put("token", token);
        }
        return data;

    }
    /**
     * 登出
     * @param token
     * @return
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }


}
