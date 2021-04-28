package com.heima.data_platform.nb.controller;

import com.heima.data_platform.nb.common.Result;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.properties.Constant;
import com.heima.data_platform.nb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 9:32
 * @description：
 * @modified By：wt
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查找用户
     * @param request 请求用户名和密码
     * @return 用户实体
     */
    @RequestMapping("/login")
    public Result login(@RequestBody HashMap<String,String> request){
        String username = request.get("username");
        String password = request.get("password");
        log.info(this.getClass()+">username:{}>password:{}",username,password);
        User user = userService.getUser(username, password);
        if (user!=null && user.getToken()!=null){
            return new Result(Constant.REQUEST_SUCCEEDED_CODE,"登陆成功",user);
        }
        /*if (user!=null && user.getToken()==null){
            return new Result(Constant.USER_ONLINE,"用户已登录");
        }*/
        return new Result(Constant.REQUEST_FAIL_CODE,"用户名或密码错误");
    }
    @RequestMapping("/logout")
    public Result logout(@RequestParam String token){
        try {
            userService.logout(token);
            return new Result(Constant.REQUEST_SUCCEEDED_CODE,"登出成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(Constant.REQUEST_FAIL_CODE,"登录过期");
    }


}
