package com.zeeyeh.discio.common.config;

import cn.hutool.core.io.FileUtil;
import com.zeeyeh.discio.common.entity.ServiceError;
import com.zeeyeh.discio.common.factory.ErrorConfigFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leon_Keiran
 * @description 异常配置
 * @date 2024/12/25/周三 13:14:05
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Configuration
public class ErrorConfig {

    @Bean
    public ErrorConfigFactory errorConfigFactory() {
        ErrorConfigFactory errorConfigFactory = new ErrorConfigFactory();
        Map<String, ServiceError> map = new LinkedHashMap<>();
        String runDir = System.getProperty("user.dir");
        File configFolder = new File(runDir, "config");
        File file = new File(configFolder, "errors.txt");
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException("错误处理配置文件目录初始化失败,请手动创建文件夹: " + file.getParentFile().getPath());
                }
            }
            // TODO: 创建错误文件
            try {
                if (!file.createNewFile()) {
                    throw new RuntimeException("错误处理配置文件初始化失败,请手动创建文件: " + file.getPath());
                }
            } catch (IOException e) {
                throw new RuntimeException("错误处理配置文件初始化失败,请手动创建文件: " + file.getPath());
            }
            return errorConfigFactory;
        }
        List<String> lines = FileUtil.readLines(file, StandardCharsets.UTF_8);
        for (String line : lines) {
            String[] parts = StringUtils.split(line, " ");
            String id = parts[0];
            int code = Integer.parseInt(parts[1]);
            String message = StringUtils.join(Arrays.copyOfRange(parts, 2, parts.length), " ");
            ServiceError serviceError = ServiceError.builder().code(code).message(message).build();
            map.put(id, serviceError);
        }
        errorConfigFactory.setErrorConfigs(map);
        return errorConfigFactory;
    }
}
