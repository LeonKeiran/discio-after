package com.zeeyeh.discio.common.annotations;

import java.lang.annotation.*;

/**
 * @author Leon_Keiran
 * @description redis库选择
 * @date 2024/12/24/周二 21:02:23
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisSelect {
    int value() default 0;
}
