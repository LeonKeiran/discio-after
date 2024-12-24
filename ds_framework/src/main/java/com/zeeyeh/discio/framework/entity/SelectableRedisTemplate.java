package com.zeeyeh.discio.framework.entity;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Leon_Keiran
 * @description redis动态选择模板
 * @date 2024/12/24/周二 21:18:47
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class SelectableRedisTemplate extends StringRedisTemplate {

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        connection.select(RedisSelectSupport.getSelect());
        return super.preProcessConnection(connection, existingConnection);
    }
}

