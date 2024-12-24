package com.zeeyeh.discio.common.annotations.validator;

import com.zeeyeh.discio.common.web.validator.AtLeastOneNotNullValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Leon_Keiran
 * @description 至少需有一项字段校验
 * @date 2024/12/24/周二 21:03:21
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
public @interface AtLeastOntNotNull {
    String message() default "至少需有一项字段不为空";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] fields();
}
