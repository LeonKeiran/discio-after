package com.zeeyeh.discio.framework.web.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.zeeyeh.discio.framework.config.DsSecureResponseProperties;
import com.zeeyeh.discio.framework.entity.Result;
import jakarta.annotation.Resource;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Leon_Keiran
 * @description 响应处理器
 * @date 2024/12/24/周二 21:22:27
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@RestControllerAdvice
@Component
public class ResponseHandle implements ResponseBodyAdvice<Object> {

    @Resource
    private DsSecureResponseProperties responseProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result<?> result) {
            Map<String, Object> headers = result.getHeaders();
            if (headers != null) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    response.getHeaders().add(key, value.toString());
                }
            }
            PropertyFilter filter = (source, name, value) -> !getExcludeFields().contains(name);
            return JSON.toJSONString(body, filter, JSONWriter.Feature.WriteEnumUsingOrdinal, JSONWriter.Feature.WriteBooleanAsNumber, JSONWriter.Feature.WriteMapNullValue);
        }
        return Result.success(body);
    }

    public List<String> getExcludeFields() {
        List<String> excludeFields = this.responseProperties.getExcludeFields();
        List<String> list = new ArrayList<>();
        list.add("headers");
        if (excludeFields == null) {
            return list;
        }
        list.addAll(excludeFields);
        return new ArrayList<>();
    }
}
