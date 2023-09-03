package com.example.bootvueadmin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

// java bean
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")  //2022-06-22
    private Date birth;
    private BigDecimal account;
    private String phone;
    private String email;
    private String role;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
