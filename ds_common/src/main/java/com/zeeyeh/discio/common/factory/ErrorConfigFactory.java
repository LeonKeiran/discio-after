package com.zeeyeh.discio.common.factory;

import com.zeeyeh.discio.common.entity.ServiceError;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Leon_Keiran
 * @description 错误配置工厂
 * @date 2024/12/25/周三 13:14:52
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Component
@Data
public class ErrorConfigFactory {

    private Map<String, ServiceError> errorConfigs;

    public ErrorConfigFactory() {
        this(new LinkedHashMap<>());
    }

    public ErrorConfigFactory(Map<String, ServiceError> errorConfigs) {
        this.errorConfigs = errorConfigs;
    }

    public ServiceError read(String id) {
        if (!getErrorConfigs().containsKey(id)) {
            return ServiceError.builder()
                    .code(-1)
                    .message("系统异常，请稍后再试")
                    .build();
        }
        return getErrorConfigs().get(id);
    }
}
