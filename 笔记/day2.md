## Hutool工具类
[https://www.hutool.cn/](https://www.hutool.cn/)

```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.7.20</version>
</dependency>
```

学习JSONUtil的使用

## DEBUG技能
必须掌握
- 后台Java debug
- 前台页面的 debug

## 学习JDBC的使用（虽然很孬）
```java
package com.example.bootvueadmin.utils;

import com.example.bootvueadmin.entity.User;

import java.sql.*;

public class JDBCUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/boot-vue-admin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2b8";
        return DriverManager.getConnection(url, "root", "123456");
    }


    public User executeQueryUser(String username, String password) {
        // where 1=1 or
        String sql = "select * from user where username = " + username + " and password = " + password;
        // 语法糖  try-resources
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                User user = new User();
                user.setUsername(username1);
                user.setPassword(password1);
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
```

## 学习Postman的使用

一个非常强大的接口测试工具

## JDBC升级懒人ORM-Mybatis

```xml
 <dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>
```

mybatis文档：[https://mybatis.net.cn/](https://mybatis.net.cn/)

了解什么是 namespace

学习 `<select>` 标签

学习 `#{}` 预编译语法

IDEA安装 MybatisX插件

## 学习Spring的IOC

学习注入Bean  @Autowired注解

演示错误：required a bean of type 'com.example.bootvueadmin.mapper.UserMapper' that could not be found.
并理解为什么出现

演示错误：org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.example.bootvueadmin.mapper.UserMapper.selectUser
并理解为什么

参数不匹配（当多个参数的时候会出现这个问题）

学习注解 @Param("username") 参数注解

## UserMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.bootvueadmin.mapper.UserMapper">

    <select id="selectUser" resultType="com.example.bootvueadmin.entity.User">
        select * from user where username = #{username} and password = #{password};
    </select>

</mapper>
```