package com.heima.data_platform.emp.controller;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageInfo;
import com.heima.data_platform.common.Result;
import com.heima.data_platform.emp.common.System;
import com.heima.data_platform.emp.service.SystemService;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 15:08
 * @description：
 * @modified By：wt
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SystemController {
    private final SystemService systemService;
    final CheckUtil checkUtil;
    @Autowired
    public SystemController(SystemService systemService, CheckUtil checkUtil) {
        this.systemService = systemService;
        this.checkUtil = checkUtil;
    }

    /**
     * 新增系统
     * @param system
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public Result addSystem(System system, @RequestParam String token){
        //log.info("group>>>>>{}+token>>>>>>{}",group,token);
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null){
            try {
                system.setMark_name(IdUtil.simpleUUID());
                system.setOperate_by(redisUser.getUsername());
                systemService.addSystem(system);
                return new Result(200,"添加成功");
            }catch (Exception e){
                e.printStackTrace();
            }
            return new Result(0,"添加失败");
        }
        return new Result(0,"请登录后添加");
    }
    @RequestMapping("/get")
    public Result getSystem(System system,String token,@RequestParam(defaultValue = "1",value = "page") Integer pageNum, @RequestParam(defaultValue = "10",value = "limit") Integer pageSize){
        PageInfo<System> systems = systemService.getSystem(pageNum,pageSize,system);
        if (systems!=null){
            return new Result(200,"获取成功",systems);
        }
        return new Result(0,"获取失败");
    }

    @RequestMapping("/delete")
    public Result deleteSystem(int id,@RequestParam("token") String token){
        if (!checkUtil.checkToken(token)){
            return new Result(0,"没有权限");
        }
        try {
            systemService.deleteSystem(id);
            return new Result(200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(0,"删除失败");
    }

    @RequestMapping("/update")
    public Result updateSystem(System system,@RequestParam("token") String token){
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null) {
            try {
                system.setOperate_by(redisUser.getUsername());
                systemService.updateSystem(system);
                return new Result(200, "更新成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(0,"更新失败");
    }

}
