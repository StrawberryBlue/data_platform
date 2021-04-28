package com.heima.data_platform.emp.controller;

import com.heima.data_platform.emp.common.Group;
import com.heima.data_platform.emp.service.GroupService;
import com.heima.data_platform.nb.common.Result;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wt
 * @date ：Created in 2021-04-28 14:35
 * @description：
 * @modified By：wt
 */
@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;
    final CheckUtil checkUtil;
    @Autowired
    public GroupController(GroupService groupService, CheckUtil checkUtil) {
        this.groupService = groupService;
        this.checkUtil = checkUtil;
    }
    @RequestMapping("/add")
    public Result addGroup(Group group,@RequestParam String token){
        //log.info("group>>>>>{}+token>>>>>>{}",group,token);
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null){
            groupService.addGroup(group);
        }
        return new Result(0,"添加失败");
    }
}
