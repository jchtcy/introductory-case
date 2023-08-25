package com.jch.introductoryadmin.Interceptor;

import com.alibaba.fastjson2.JSON;
import com.jch.introductorycommom.utils.JwtUtil;
import com.jch.introductorycommom.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtValidareInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Token");
        log.debug(request.getRequestURI() + "需要验证: " + token);
        if (token != null) {
            try {
                jwtUtil.parseToken(token);
                log.debug(request.getRequestURI() + "验证通过");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug(request.getRequestURI() + "验证失败, 禁止访问");

        response.setContentType("application/json;charset=utf-8");
        ResultUtil<Object> fail = ResultUtil.fail("20003", "Jwt无效,请重新登录");
        response.getWriter().write(JSON.toJSONString(fail));
        return false;
    }
}