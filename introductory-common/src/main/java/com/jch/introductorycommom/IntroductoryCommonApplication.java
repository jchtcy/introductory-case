package com.jch.introductorycommom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jch.introductorycommom"})
public class IntroductoryCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntroductoryCommonApplication.class, args);
    }

}
