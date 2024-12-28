package com.zeeyeh.discio.framework.model.request;

import com.zeeyeh.discio.system.entity.UserGender;
import com.zeeyeh.discio.system.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Leon_Keiran
 * @description 用户更新请求模型
 * @date 2024/12/28/周六 19:53:07
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserUpdateRequestModel {

    /**
     * 用户ID
     * @date 2024/12/28/周六 19:53:38
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private Long id;

    /**
     * 用户名
     * @date 2024/12/28/周六 19:53:41
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String username;

    /**
     * 昵称
     * @date 2024/12/28/周六 19:53:45
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String nickname;

    /**
     * 邮箱
     * @date 2024/12/28/周六 19:53:48
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String email;

    /**
     * 手机号
     * @date 2024/12/28/周六 19:53:53
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String phone;
    /**
     * 头像
     * @date 2024/12/28/周六 19:53:55
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String avatar;

    /**
     * 性别
     * @date 2024/12/28/周六 19:53:59
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private UserGender gender;

    /**
     * 生日
     * @date 2024/12/28/周六 19:54:03
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private Long birthday;

    /**
     * 个性签名
     * @date 2024/12/28/周六 19:54:07
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String signature;

    /**
     * 状态
     * @date 2024/12/28/周六 19:54:09
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private UserStatus status;

    /**
     * 创建时间
     * @date 2024/12/28/周六 19:54:12
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private Long createTime;

    /**
     * 更新时间
     * @date 2024/12/28/周六 19:54:16
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private Long updateTime;
} 