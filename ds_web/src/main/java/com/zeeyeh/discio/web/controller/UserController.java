package com.zeeyeh.discio.web.controller;

import com.zeeyeh.discio.common.annotations.ResponseFormatter;
import com.zeeyeh.discio.framework.entity.Result;
import com.zeeyeh.discio.framework.model.request.UserLoginRequestModel;
import com.zeeyeh.discio.framework.model.request.UserRegisterRequestModel;
import com.zeeyeh.discio.framework.model.request.UserSearchRequestModel;
import com.zeeyeh.discio.framework.model.request.UserUpdateRequestModel;
import com.zeeyeh.discio.framework.service.UserService;
import com.zeeyeh.discio.system.entity.User;
import com.zeeyeh.discio.system.entity.UserStatus;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leon_Keiran
 * @description 用户接口
 * @date 2024/12/25/周三 15:59:44
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@RestController
@RequestMapping("/users")
@ResponseFormatter
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:15:56
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 注册请求参数
     * @return 注册结果
     */
    @PostMapping("/register")
    public User register(@RequestBody UserRegisterRequestModel model) {
        return userService.register(model);
        // return Result.success(null);
    }

    /**
     * 登录
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:15:49
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 登录请求参数
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody UserLoginRequestModel model) {
        return userService.login(model);
    }

    /**
     * 退出登录
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:15:41
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param token token
     */
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
    }

    /**
     * 搜索用户
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:43:19
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 搜索请求参数
     * @return 搜索结果
     */
    @GetMapping("/search")
    public Result<List<User>> searchUsers(@RequestBody UserSearchRequestModel model) {
        List<User> users = userService.search(model);
        return Result.success(users);
    }

    /**
     * 更新用户资料
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:56:28
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 更新请求参数
     * @return 更新结果
     */
    @PostMapping("/update")
    public Result<User> update(@RequestBody UserUpdateRequestModel model) {
        User updatedUser = userService.update(model);
        return Result.success(updatedUser);
    }
}
