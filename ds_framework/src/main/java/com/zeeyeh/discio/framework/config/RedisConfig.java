package com.zeeyeh.discio.framework.config;

import com.zeeyeh.discio.framework.entity.SelectableRedisTemplate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Leon_Keiran
 * @description Redis配置
 * @date 2024/12/24/周二 21:16:08
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(RedisProperties.class)
@AllArgsConstructor
public class RedisConfig {
    private RedisProperties properties;

    @Bean
    @Primary
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(properties.getHost());
        configuration.setPort(properties.getPort());
        configuration.setPassword(RedisPassword.of(properties.getPassword()));
        configuration.setDatabase(properties.getDatabase());
        return new JedisConnectionFactory(configuration, getJedisClientConfiguration());
    }

    private JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        if (properties.getSsl().isEnabled()) {
            builder.useSsl();
        }
        if (properties.getTimeout() != null) {
            Duration timeout = properties.getTimeout();
            builder.readTimeout(timeout)
                    .connectTimeout(timeout);
        }
        RedisProperties.Pool pool = properties.getJedis().getPool();
        if (pool != null) {
            builder.usePooling().poolConfig(jedisPoolConfig(pool));
        }
        return builder.build();
    }

    private JedisPoolConfig jedisPoolConfig(RedisProperties.Pool pool) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        if (pool.getMaxWait() != null) {
            config.setMaxWait(pool.getMaxWait());
        }
        return config;
    }

    @Bean(name = "redisTemplate")
    @Primary
    public SelectableRedisTemplate redisTemplate() {
        SelectableRedisTemplate selectableRedisTemplate = new SelectableRedisTemplate();
        selectableRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        return selectableRedisTemplate;
    }
}
