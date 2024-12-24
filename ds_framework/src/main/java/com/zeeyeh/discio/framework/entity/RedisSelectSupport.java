package com.zeeyeh.discio.framework.entity;

/**
 * @author Leon_Keiran
 * @description Redis动态选择支持类
 * @date 2024/12/24/周二 21:17:40
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class RedisSelectSupport {
    private static final ThreadLocal<Integer> SELECT_INDEX = new ThreadLocal<>();

    public static void select(int index) {
        SELECT_INDEX.set(index);
    }

    public static int getSelect() {
        return SELECT_INDEX.get();
    }
}
