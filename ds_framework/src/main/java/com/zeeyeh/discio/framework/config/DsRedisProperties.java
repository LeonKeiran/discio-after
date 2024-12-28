package com.zeeyeh.discio.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Leon_Keiran
 * @description 系统redis配置
 * @date 2024/12/26/周四 22:23:41
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Component
@Data
@ConfigurationProperties(prefix = "ds.redis")
public class DsRedisProperties {
    private long loginExpireSeconds;
}
