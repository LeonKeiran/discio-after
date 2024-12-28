package com.zeeyeh.discio.system.entity;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Leon_Keiran
 * @description 用户性别枚举
 * @date 2024/12/24/周二 21:29:47
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@AllArgsConstructor
@Getter
public enum UserGender {
    /**
     * 女性
     * @date 2024/12/24/周二 21:30:59
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    WOMAN(0),
    /**
     * 男性
     * @date 2024/12/24/周二 21:30:57
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    MAN(1),
    /**
     * 未知性别
     * @date 2024/12/24/周二 21:30:53
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>     */
    UNKNOWN(2),
    ;

    @EnumValue
    @JsonValue
    private final int code;

    @JsonCreator
    public static UserGender fromCode(int code) {
        for (UserGender gender : UserGender.values()) {
            if (gender.code == code) {
                return gender;
            }
        }
        return UNKNOWN;
    }
}
