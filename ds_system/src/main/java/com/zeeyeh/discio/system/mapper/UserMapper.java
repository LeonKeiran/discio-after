package com.zeeyeh.discio.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeeyeh.discio.system.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Leon_Keiran
 * @description 用户数据层
 * @date 2024/12/24/周二 21:35:15
 * @github <a href="https://github.com/LeonKeiran">https://github.com/LeonKeiran</a>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
