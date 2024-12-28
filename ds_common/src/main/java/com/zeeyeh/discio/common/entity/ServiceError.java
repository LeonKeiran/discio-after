package com.zeeyeh.discio.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Leon_Keiran
 * @description 业务错误
 * @date 2024/12/25/周三 11:58:47
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Getter
@AllArgsConstructor
@Builder
public class ServiceError {
    private int code;
    private String message;
}
