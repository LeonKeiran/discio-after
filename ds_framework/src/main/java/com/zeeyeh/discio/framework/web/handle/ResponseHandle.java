package com.zeeyeh.discio.framework.web.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.zeeyeh.discio.common.annotations.ResponseFormatter;
import com.zeeyeh.discio.common.entity.ServiceHandlerException;
import com.zeeyeh.discio.framework.config.DsSecureResponseProperties;
import com.zeeyeh.discio.framework.entity.Result;
import com.zeeyeh.discio.system.entity.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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



    public Map<String, Object> formatResponse(Object body) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class);
        filter.getExcludes().addAll(this.getExcludeFields());
        Result<?> result;
        if (!(body instanceof Result<?>)) {
            result = Result.success(body);
        } else {
            result = (Result<?>) body;
        }
        String jsonString = JSON.toJSONString(result, filter, JSONWriter.Feature.WriteEnumUsingOrdinal, JSONWriter.Feature.WriteBooleanAsNumber, JSONWriter.Feature.WriteMapNullValue);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return Map.of(
                "code", jsonObject.get("code"),
                "timestamp", jsonObject.get("timestamp"),
                "message", jsonObject.get("message"),
                "data", jsonObject.get("data")
        );
    }

    public List<String> getExcludeFields() {
        List<String> excludeFields = this.responseProperties.getExcludeFields();
        List<String> list = new ArrayList<>();
        list.add("headers");
        if (excludeFields == null) {
            return list;
        }
        list.addAll(excludeFields);
        return list;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();
        Class<?> aClass = returnType.getDeclaringClass();
        if (method == null) {
            throw new ServiceHandlerException(-1, "系统异常，请联系管理员");
        }
        boolean annotationPresent = aClass.isAnnotationPresent(ResponseFormatter.class);
        boolean annotationPresent1 = method.isAnnotationPresent(ResponseFormatter.class);
        return annotationPresent || annotationPresent1;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (body instanceof Result<?> result) {
            for (Map.Entry<String, Object> entry : result.getHeaders().entrySet()) {
                response.getHeaders().set(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return formatResponse(body);
    }
}
