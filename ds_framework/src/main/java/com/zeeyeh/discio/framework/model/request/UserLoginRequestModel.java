package com.zeeyeh.discio.framework.model.request;

import com.zeeyeh.discio.common.annotations.validator.AtLeastOntNotNull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon_Keiran
 * @description 用户登录请求数据模型
 * @date 2024/12/24/周二 21:43:59
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@AtLeastOntNotNull(fields = {
        "username",
        "email"
}, message = "用户名、昵称、邮箱至少有一个不能为空")
public class UserLoginRequestModel {

    /**
     * 用户名
     * @date 2024/12/24/周二 22:03:29
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String username;

    /**
     * 邮箱地址
     * @date 2024/12/24/周二 22:03:35
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String email;

    /**
     * 验证码
     * @date 2024/12/24/周二 22:03:39
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "验证码不能为空")
    private String code;

    /**
     * 密码
     * @date 2024/12/24/周二 22:03:43
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "密码不能为空")
    private String password;
}
