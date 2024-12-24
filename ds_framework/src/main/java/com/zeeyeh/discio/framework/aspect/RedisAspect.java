package com.zeeyeh.discio.framework.aspect;

import com.zeeyeh.discio.common.annotations.RedisSelect;
import com.zeeyeh.discio.framework.entity.RedisSelectSupport;
import com.zeeyeh.discio.framework.entity.SelectableRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Leon_Keiran
 * @description Redis动态切换库切面处理类
 * @date 2024/12/24/周二 21:14:01
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Aspect
@Component
@Slf4j
public class RedisAspect {

    @Value("${ds.redis.enable}")
    private boolean enable;

    @Value("${ds.redis.default}")
    private int defaultDatabase;

    @Around("execution(* com.zeeyeh.discio.framework.factory.RedisFactory.*(..))")
    public Object redisAround(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if (enable) {
            try {
                result = point.proceed();
            } catch (Exception e) {
                log.error("redis error: ", e);
            }
        }
        return result;
    }

    @Around("@annotation(com.zeeyeh.discio.common.annotations.RedisSelect)")
    @ConditionalOnBean(SelectableRedisTemplate.class)
    public Object configRedis(ProceedingJoinPoint point) throws Throwable {
        int db = defaultDatabase;
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            RedisSelect redisSelect = method.getAnnotation(RedisSelect.class);
            if (redisSelect != null) {
                db = redisSelect.value();
            }
            RedisSelectSupport.select(db);
            return point.proceed();
        } finally {
            log.debug("redis reset {} to {}", db, defaultDatabase);
            RedisSelectSupport.select(defaultDatabase);
        }
    }
}
