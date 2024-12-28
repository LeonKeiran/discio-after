package com.zeeyeh.discio.framework.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeeyeh.discio.common.annotations.RedisSelect;
import com.zeeyeh.discio.common.entity.ServiceHandlerException;
import com.zeeyeh.discio.common.factory.ErrorConfigFactory;
import com.zeeyeh.discio.common.utils.SecureUtil;
import com.zeeyeh.discio.common.utils.TokenUtil;
import com.zeeyeh.discio.framework.config.DsRedisProperties;
import com.zeeyeh.discio.framework.entity.Result;
import com.zeeyeh.discio.framework.factory.RedisFactory;
import com.zeeyeh.discio.framework.model.request.UserLoginRequestModel;
import com.zeeyeh.discio.framework.model.request.UserRegisterRequestModel;
import com.zeeyeh.discio.framework.model.request.UserSearchRequestModel;
import com.zeeyeh.discio.framework.model.request.UserUpdateRequestModel;
import com.zeeyeh.discio.framework.service.UserService;
import com.zeeyeh.discio.system.entity.User;
import com.zeeyeh.discio.system.entity.UserStatus;
import com.zeeyeh.discio.system.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leon_Keiran
 * @description 用户业务层实现类
 * @date 2024/12/24/周二 21:38:16
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    ErrorConfigFactory errorConfigFactory;

    @Value("${ds.secure.aes.key}")
    private String key;

    @Value("${ds.secure.aes.iv}")
    private String iv;

    @Resource
    private DsRedisProperties redisProperties;

    @Resource
    RedisFactory redisFactory;

    /**
     * 用户注册
     *
     * @param model 用户注册请求模型
     * @return 用户实体类
     * @author Leon_Keiran
     * @date 2024/12/24/周二 22:06:34
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     */
    @Override
    public User register(UserRegisterRequestModel model) {
        boolean existsed = this.exists(new QueryWrapper<User>().eq("username", model.getUsername()));
        if (existsed) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.register.existed"));
        }
        long counted = this.count();
        User user = User.builder()
                .id(counted + 1)
                .username(model.getUsername())
                .nickname(model.getNickname())
                .password(SecureUtil.encryptPassword(model.getPassword(), key, iv))
                .status(UserStatus.NORMAL)
                .build();
        if (!this.save(user)) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.register.failed"));
        }
        return user;
    }

    /**
     * 用户登录
     *
     * @param model 用户登录请求模型
     * @return 用户实体类
     * @author Leon_Keiran
     * @date 2024/12/24/周二 22:06:25
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     */
    @Override
    @RedisSelect(0)
    public Result<User> login(UserLoginRequestModel model) {
        User user;
        if (StringUtils.isNotEmpty(model.getUsername())) {
            // TODO: 实现用户名登录
            boolean existsed = this.exists(new QueryWrapper<User>().eq("username", model.getUsername()));
            if (!existsed) {
                throw new ServiceHandlerException(errorConfigFactory.read("user.register.not.existed"));
            }
            user = this.getOne(new QueryWrapper<User>().eq("username", model.getUsername()));
        } else if (StringUtils.isNotEmpty(model.getEmail())) {
            // TODO: 实现邮箱登录
            boolean existsed = this.exists(new QueryWrapper<User>().eq("email", model.getEmail()));
            if (!existsed) {
                throw new ServiceHandlerException(errorConfigFactory.read("user.register.not.existed"));
            }
            user = this.getOne(new QueryWrapper<User>().eq("email", model.getEmail()));
        } else {
            throw new ServiceHandlerException(errorConfigFactory.read("user.login.failed"));
        }
        if (user == null) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.login.failed"));
        }
        String inputPassword = SecureUtil.encryptPassword(model.getPassword(), key, iv);
        if (!StringUtils.equals(inputPassword, user.getPassword())) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.login.failed.password"));
        }
        String token = TokenUtil.getToken(user.getId(), user.getUsername(), key.getBytes());
        long loginExpireSeconds = redisProperties.getLoginExpireSeconds();
        redisFactory.set("user:login:" + user.getId(), token, loginExpireSeconds);
        return Result.success(user).header("Authorization", "Bearer " + token);
    }

    /**
     * 用户退出登录
     *
     * @param token 用户token
     * @author Leon_Keiran
     * @date 2024/12/28/周六 18:42:12
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     */
    @Override
    public void logout(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        String id = (String) JWTUtil.parseToken(token).getPayload("id");
        if (StringUtils.isNotEmpty(id)) {
            redisFactory.delete("user:login:" + id);
        }
    }

    /**
     * 搜索用户
     * @author Leon_Keiran
     * @date 2024/12/28/周六 19:46:33
     * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
     * @param model 用户搜索请求模型
     * @return 用户列表
     */
    @Override
    public List<User> search(UserSearchRequestModel model) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        
        if (StringUtils.isNotEmpty(model.getUsername())) {
            queryWrapper.lambda().like(User::getUsername, model.getUsername());
        }
        if (StringUtils.isNotEmpty(model.getNickname())) {
            queryWrapper.lambda().like(User::getNickname, model.getNickname());
        }
        if (StringUtils.isNotEmpty(model.getEmail())) {
            queryWrapper.lambda().like(User::getEmail, model.getEmail());
        }
        if (StringUtils.isNotEmpty(model.getPhone())) {
            queryWrapper.lambda().like(User::getPhone, model.getPhone());
        }
        if (model.getBirthdayStart() != null && model.getBirthdayEnd() != null) {
            queryWrapper.lambda().between(User::getBirthday, model.getBirthdayStart(), model.getBirthdayEnd());
        }
        if (model.getStatus() != null) {
            queryWrapper.lambda().eq(User::getStatus, model.getStatus());
        }
        if (model.getCreateTimeStart() != null && model.getCreateTimeEnd() != null) {
            queryWrapper.lambda().between(User::getCreateTime, model.getCreateTimeStart(), model.getCreateTimeEnd());
        }
        if (model.getUpdateTimeStart() != null && model.getUpdateTimeEnd() != null) {
            queryWrapper.lambda().between(User::getUpdateTime, model.getUpdateTimeStart(), model.getUpdateTimeEnd());
        }
        
        return this.list(queryWrapper);
    }

    @Override
    public User update(UserUpdateRequestModel model) {
        if (model == null || model.getId() == null) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.update.invalid"));
        }
        User user = this.getById(model.getId());
        if (user == null) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.not.found"));
        }

        if (StringUtils.isNotEmpty(model.getUsername())) {
            user.setUsername(model.getUsername());
        }
        if (StringUtils.isNotEmpty(model.getNickname())) {
            user.setNickname(model.getNickname());
        }
        if (StringUtils.isNotEmpty(model.getEmail())) {
            user.setEmail(model.getEmail());
        }
        if (StringUtils.isNotEmpty(model.getPhone())) {
            user.setPhone(model.getPhone());
        }
        if (StringUtils.isNotEmpty(model.getAvatar())) {
            user.setAvatar(model.getAvatar());
        }
        if (model.getGender() != null) {
            user.setGender(model.getGender());
        }
        if (model.getBirthday() != null) {
            user.setBirthday(model.getBirthday());
        }
        if (StringUtils.isNotEmpty(model.getSignature())) {
            user.setSignature(model.getSignature());
        }
        if (model.getStatus() != null) {
            user.setStatus(model.getStatus());
        }
        if (model.getCreateTime() != null) {
            user.setCreateTime(model.getCreateTime());
        }
        if (model.getUpdateTime() != null) {
            user.setUpdateTime(model.getUpdateTime());
        }

        boolean updated = this.updateById(user);
        if (!updated) {
            throw new ServiceHandlerException(errorConfigFactory.read("user.update.failed"));
        }
        return user;
    }
}
