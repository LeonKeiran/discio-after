package com.zeeyeh.discio.framework.model.request;

import com.zeeyeh.discio.system.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leon_Keiran
 * @description 用户搜索请求数据模型
 * @date 2024/12/28/周六 19:35:50
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSearchRequestModel {

    /**
     * 用户名
     * @date 2024/12/28/周六 19:40:55
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String username;

    /**
     * 昵称
     * @date 2024/12/28/周六 19:40:59
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String nickname;

    /**
     * 邮箱
     * @date 2024/12/28/周六 19:41:02
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String email;

    /**
     * 手机号
     * @date 2024/12/28/周六 19:41:06
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String phone;

    /**
     * 生日开始时间
     * @date 2024/12/28/周六 19:41:09
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String birthdayStart;

    /**
     * 生日结束时间
     * @date 2024/12/28/周六 19:41:23
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String birthdayEnd;

    /**
     * 用户状态
     * @date 2024/12/28/周六 19:41:27
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private UserStatus status;

    /**
     * 创建时间开始
     * @date 2024/12/28/周六 19:41:31
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String createTimeStart;

    /**
     * 创建时间结束
     * @date 2024/12/28/周六 19:41:34
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String createTimeEnd;

    /**
     * 更新时间开始
     * @date 2024/12/28/周六 19:41:38
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String updateTimeStart;

    /**
     * 更新时间结束
     * @date 2024/12/28/周六 19:41:41
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    private String updateTimeEnd;
}
