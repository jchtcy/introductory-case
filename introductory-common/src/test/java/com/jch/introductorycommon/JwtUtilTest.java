package com.jch.introductorycommon;

import com.jch.introductorycommom.IntroductoryCommonApplication;
import com.jch.introductorycommom.domain.User;
import com.jch.introductorycommom.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IntroductoryCommonApplication.class)
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPhone("12399988877");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void testparseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMmUwY2YzYy00MzUyLTRlYTEtYTQwOS03YjI4ZWExM2Q5OWQiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY5Mjg0Mzg5OCwiZXhwIjoxNjkyODQ1Njk4fQ.YcL7S4M6yEM79APy4sjReWmUUbGw_Fl5FP_vRq08ctI";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testparseToken2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMmUwY2YzYy00MzUyLTRlYTEtYTQwOS03YjI4ZWExM2Q5OWQiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY5Mjg0Mzg5OCwiZXhwIjoxNjkyODQ1Njk4fQ.YcL7S4M6yEM79APy4sjReWmUUbGw_Fl5FP_vRq08ctI";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }
}
