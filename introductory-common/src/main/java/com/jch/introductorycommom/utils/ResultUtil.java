package com.jch.introductorycommom.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultUtil<T> {
    private static final String success = "200";
    private static final String error = "201";

    private String code;
    private String message;
    private T data;

    public static <T> ResultUtil<T> ok() {
        return new ResultUtil<>(success, "success", null);
    }

    public static <T> ResultUtil<T> ok(T data) {
        return new ResultUtil<>(success, "success", data);
    }

    public static <T> ResultUtil<T> fail(String message) {
        return new ResultUtil<>(error, message, null);
    }

    public static <T> ResultUtil<T> fail(String code, String message) {
        return new ResultUtil<>(code, message, null);
    }
}
