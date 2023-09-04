package com.example.bootvueadmin.controller;

import com.example.bootvueadmin.common.Result;
import com.example.bootvueadmin.entity.User;
import com.example.bootvueadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// SpringMVC
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<User> login(@RequestBody User user, HttpServletRequest request) {  // 把 前台发过来的 json字符串转成Java对象
        User res = userService.login(user.getUsername(), user.getPassword());
        if (res != null) {  // 用户合法
            request.getSession().setAttribute("user", res);
        }
        return Result.success(res);
    }

    /**
     * 注册接口
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<User> register(@RequestBody User user, HttpServletRequest request) {  // 把 前台发过来的 json字符串转成Java对象
        userService.register(user.getUsername(), user.getPassword());
        return Result.success();
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        System.out.println("登出之后session=============" + request.getSession().getAttribute("user"));
        response.sendRedirect("/login.html");  // 推荐后台跳转，防止缓存
    }

    @GetMapping("/list")
    public Result userList(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    @PostMapping
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")  // /1
    public Result delete(@PathVariable int id) {//这里的注解用来解析前端的参数，映射到参数id
        userService.deleteById(id);
        return Result.success();
    }
}
