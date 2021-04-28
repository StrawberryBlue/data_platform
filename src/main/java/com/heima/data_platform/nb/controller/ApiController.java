package com.heima.data_platform.nb.controller;

import com.heima.data_platform.nb.common.Result;
import com.heima.data_platform.properties.Constant;
import com.heima.data_platform.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author ：wt
 * @date ：Created in 2021-04-07 12:58
 * @description：api操作
 * @modified By：wt
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private final CheckUtil checkUtil;
    @Autowired
    public ApiController(CheckUtil checkUtil) {
        this.checkUtil = checkUtil;
    }
    @RequestMapping("/check")
    public Result checkSession(@RequestParam String token){
        if(checkUtil.checkToken(token)){
             return new Result(Constant.REQUEST_SUCCEEDED_CODE,"校验成功",null);
        };
        return new Result(Constant.REQUEST_FAIL_CODE,"请重新登录",null);
    }
}
