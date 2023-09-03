package com.example.bootvueadmin.mapper;

import com.example.bootvueadmin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    User selectUser(@Param("username") String username, @Param("password") String password);

    User selectUserByUsername(String username);

    int save(User saveuser);

}