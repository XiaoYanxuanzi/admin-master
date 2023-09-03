package com.example.bootvueadmin.service;

import cn.hutool.core.util.StrUtil;
import com.example.bootvueadmin.common.exception.CustomerException;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String DEFAULT_PASSWORD = "123";

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        // 通过Mybatis查询数据
        User user = userMapper.selectUser(username, password);
        if (user.getUsername() == null) {
            logger.error("用户名不存在");
            throw new CustomerException("用户名不存在");
        }

        if (user.getPassword() == null) {
            logger.error("密码为空");
            throw new CustomerException("密码为空");
        }
        return user;
    }

    public void register(String username, String password) throws CustomerException{
        // 通过Mybatis查询数据
        User user = userMapper.selectUserByUsername(username);
        if (user != null) {//不存在重名的用户
            throw new CustomerException("用户名重复");
        }
        User saveuser = new User(username, password);
        userMapper.save(saveuser);
    }


}
