package com.zeeyeh.discio.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeeyeh.discio.framework.entity.Result;
import com.zeeyeh.discio.framework.model.request.UserLoginRequestModel;
import com.zeeyeh.discio.framework.model.request.UserRegisterRequestModel;
import com.zeeyeh.discio.framework.model.request.UserSearchRequestModel;
import com.zeeyeh.discio.framework.model.request.UserUpdateRequestModel;
import com.zeeyeh.discio.system.entity.User;
import com.zeeyeh.discio.system.entity.UserStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Leon_Keiran
 * @description 用户业务层
 * @date 2024/12/24/周二 21:37:53
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Transactional
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @author Leon_Keiran
     * @date 2024/12/24/周二 22:06:34
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 用户注册请求模型
     * @return 用户实体类
     */
    User register(UserRegisterRequestModel model);

    /**
     * 用户登录
     * @author Leon_Keiran
     * @date 2024/12/24/周二 22:06:25
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 用户登录请求模型
     * @return 用户实体类
     */
    Result<User> login(UserLoginRequestModel model);

    /**
     * 用户退出登录
     * @author Leon_Keiran
     * @date 2024/12/28/周六 18:42:12
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param token 用户token
     */
    void logout(String token);

    /**
     * 搜索用户
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:43:57
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 用户搜索请求模型
     * @return 用户列表
     */
    List<User> search(UserSearchRequestModel model);

    /**
     * 更新用户资料
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:56:12
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 用户更新请求模型
     * @return 用户实体类
     */
    User update(UserUpdateRequestModel model);
}
