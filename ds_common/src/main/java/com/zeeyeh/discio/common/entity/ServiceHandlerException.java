package com.zeeyeh.discio.common.entity;

import lombok.Getter;

/**
 * @author Leon_Keiran
 * @description 业务处理异常
 * @date 2024/12/25/周三 12:00:11
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Getter
public class ServiceHandlerException extends RuntimeException{
    private final int code;

    public ServiceHandlerException(String message) {
        super(message);
        this.code = -1;
    }

    public ServiceHandlerException(ServiceError error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public ServiceHandlerException(int code, String message) {
        super(message);
        this.code = code;
    }
}
