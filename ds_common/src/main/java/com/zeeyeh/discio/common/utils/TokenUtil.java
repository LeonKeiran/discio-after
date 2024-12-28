package com.zeeyeh.discio.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;

/**
 * @author Leon_Keiran
 * @description token工具类
 * @date 2024/12/25/周三 14:24:30
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class TokenUtil {
    public static String getToken(long id, String username, byte[] key) {
        return JWT.create()
                .setIssuedAt(DateUtil.date())
                .setPayload("id", id)
                .setPayload("username", username)
                .setKey(key)
                .sign();
    }

    public static boolean verifyToken(String token, byte[] key) {
        return JWT.of(token).setKey(key).validate(0);
    }
}
