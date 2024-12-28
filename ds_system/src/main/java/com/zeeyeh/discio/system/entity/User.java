package com.zeeyeh.discio.system.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zeeyeh.discio.system.data.type.handlers.VarcharToLongTypeHandler;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Leon_Keiran
 * @description 用户信息表
 * @date 2024/12/24/周二 21:26:23
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Data
@TableName("ds_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private String username;

    private String nickname;

    @NotNull
    @JSONField(serialize = false)
    private String password;

    private String email;

    private String phone;

    private String avatar;

    private UserGender gender;

    @TableField(typeHandler = VarcharToLongTypeHandler.class)
    private Long birthday;

    private String signature;

    @TableField(typeHandler = VarcharToLongTypeHandler.class)
    private Long createTime;

    @TableField(typeHandler = VarcharToLongTypeHandler.class)
    private Long updateTime;

    @NotNull
    private UserStatus status;

    @TableLogic
    @JSONField(serialize = false)
    private Integer deleted;
}
