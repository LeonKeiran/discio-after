package com.zeeyeh.discio.framework.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon_Keiran
 * @description 用户注册请求数据模型
 * @date 2024/12/24/周二 22:02:11
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRegisterRequestModel {

    /**
     * 用户名
     * @date 2024/12/24/周二 22:03:49
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "用户名不能为空")
    private String username;

    /**
     * 昵称
     * @date 2024/12/24/周二 22:03:55
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "昵称不能为空")
    private String nickname;

    /**
     * 邮箱地址
     * @date 2024/12/24/周二 22:03:58
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "邮箱地址不能为空")
    private String email;

    /**
     * 验证码
     * @date 2024/12/24/周二 22:04:08
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "验证码不能为空")
    private String code;

    /**
     * 密码
     * @date 2024/12/24/周二 22:04:17
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    @NotNull(message = "密码不能为空")
    private String password;
}
