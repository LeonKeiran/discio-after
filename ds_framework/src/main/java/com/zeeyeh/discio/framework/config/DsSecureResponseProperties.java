package com.zeeyeh.discio.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Leon_Keiran
 * @description 响应配置
 * @date 2024/12/24/周二 21:15:27
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Component
@Data
@ConfigurationProperties(prefix = "ds.secure.response")
public class DsSecureResponseProperties {
    /**
     * 响应过滤字段列表
     * @date 2024/12/24/周二 21:15:43
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private List<String> excludeFields;
}
