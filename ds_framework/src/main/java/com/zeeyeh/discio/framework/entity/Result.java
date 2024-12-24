package com.zeeyeh.discio.framework.entity;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leon_Keiran
 * @description 响应结果实体类
 * @date 2024/12/24/周二 21:18:00
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Result<T> {
    private int code;
    private String message;
    private long timestamp;
    private T data;
    @Singular
    private Map<String, Object> headers = new HashMap<>();

    public static <T> Result<T> success() {
        return any(0, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return any(0, "success", data);
    }

    public static <T> Result<T> success(int code, T data) {
        return any(code, "success", data);
    }

    public static <T> Result<T> error() {
        return any(0, "error", null);
    }

    public static <T> Result<T> error(T data) {
        return any(0, "error", data);
    }

    public static <T> Result<T> error(int code) {
        return any(code, "error", null);
    }

    public static <T> Result<T> error(int code, T data) {
        return any(code, "error", data);
    }

    public static <T> Result<T> notfound() {
        return any(-1000, "notfound", null);
    }

    public static <T> Result<T> notfound(T data) {
        return any(-1000, "notfound", data);
    }

    public static <T> Result<T> accessed() {
        return any(-1000, "no access", null);
    }

    public static <T> Result<T> accessed(T data) {
        return any(-1000, "no access", data);
    }

    public static <T> Result<T> any(int code, String message, T data) {
        return any(code, message, System.currentTimeMillis(), data);
    }

    public static <T> Result<T> any(int code, String message, long timestamp, T data) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .timestamp(timestamp)
                .data(data)
                .build();
    }

    /**
     * 添加header
     * @author Leon_Keiran
     * @date 2024/12/22/周日 21:19:01
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param key 键
     * @param value 值
     * @return 返回ResponseHeaders对象
     */
    public Result<T> header(String key, Object value) {
        if (headers.containsKey(key)) {
            return this;
        }
        headers.put(key, value);
        return this;
    }
}
