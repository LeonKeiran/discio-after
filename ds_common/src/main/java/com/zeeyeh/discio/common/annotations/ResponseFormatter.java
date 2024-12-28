package com.zeeyeh.discio.common.annotations;

import com.zeeyeh.discio.common.entity.ResponseType;

import java.lang.annotation.*;

/**
 * @author Leon_Keiran
 * @description 响应内容格式化
 * @date 2024/12/25/周三 16:45:28
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Documented
@Target({
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseFormatter {
    /**
     * 响应格式化类型。默认: JSON
     * @author Leon_Keiran
     * @date 2024/12/25/周三 16:49:39
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @return ResponseType
     */
    ResponseType value() default ResponseType.JSON;
}
