package com.zeeyeh.discio.common.web.validator;

import com.zeeyeh.discio.common.annotations.validator.AtLeastOntNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author Leon_Keiran
 * @description 至少需有一项字段校验处理器
 * @date 2024/12/24/周二 21:11:18
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOntNotNull, Object> {

    private String[] fields;

    @Override
    public void initialize(AtLeastOntNotNull constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        try {
            return Stream.of(fields).anyMatch(field -> {
                try {
                    Method method = value.getClass().getMethod("get" + capitalize(field));
                    return method.invoke(value) != null;
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
