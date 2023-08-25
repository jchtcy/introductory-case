package com.jch.introductoryadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.jch.introductoryadmin", "com.jch.introductorycommom"})
@MapperScan("com.jch.introductoryadmin.dao")
public class IntroductoryAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroductoryAdminApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
