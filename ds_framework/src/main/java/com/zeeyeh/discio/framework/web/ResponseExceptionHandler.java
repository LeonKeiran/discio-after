package com.zeeyeh.discio.framework.web;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import com.zeeyeh.discio.common.entity.ServiceHandlerException;
import com.zeeyeh.discio.framework.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;


/**
 * @author Leon_Keiran
 * @description 响应异常处理器
 * @date 2024/12/26/周四 18:20:50
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ServiceHandlerException.class)
    public Result<Object> handlerServiceException(ServiceHandlerException e) {
        e.fillInStackTrace();
        return Result.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> handlerException(Exception e) {
        e.fillInStackTrace();
        return Result.builder()
                .code(-1)
                .message("系统异常，请稍后再试！")
                .data(e.getMessage())
                // .data(HexUtil.encodeHexStr(Base64.encode(e.getMessage(), StandardCharsets.UTF_16LE)))
                .build();
    }
}
