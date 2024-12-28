package com.zeeyeh.discio.system.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Leon_Keiran
 * @description 用户状态
 * @date 2024/12/24/周二 21:31:30
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@Getter
public enum UserStatus {
    /**
     * 正常状态
     * @date 2024/12/24/周二 21:34:39
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    NORMAL(0),
    /**
     * 冻结状态
     * @date 2024/12/24/周二 21:34:36
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    FREEZE(1),
    /**
     * 删除状态
     * @date 2024/12/24/周二 21:34:23
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    DELETE(2),
    ;

    @EnumValue
    @JsonValue
    private final int code;

    @JsonCreator
    public static UserStatus fromCode(int code) {
        for (UserStatus status : UserStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        return NORMAL;
    }
}
