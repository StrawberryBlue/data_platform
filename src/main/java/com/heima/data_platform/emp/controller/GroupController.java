package com.heima.data_platform.emp.controller;

import com.heima.data_platform.emp.common.Group;
import com.heima.data_platform.emp.service.GroupService;
import com.heima.data_platform.nb.common.Result;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 添加集团
     * @param group
     * @param token
     * @return
     */
    @RequestMapping("/add")
    public Result addGroup(Group group,@RequestParam String token){
        //log.info("group>>>>>{}+token>>>>>>{}",group,token);
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null){
            try {
                group.setOperate_by(redisUser.getUsername());
                groupService.addGroup(group);
                return new Result(200,"添加成功");
            }catch (Exception e){
                e.printStackTrace();
            }
            return new Result(0,"添加失败");
        }
        return new Result(0,"请登录后添加");
    }

    /**
     * 获取所有集团
     * @return
     */
    @RequestMapping("/get")
    public Result getGroup(String token,@RequestParam(defaultValue = "1",value = "page") Integer pageNum, @RequestParam(defaultValue = "10",value = "limit") Integer pageSize){
        List<Group> groups = groupService.getGroup(pageNum,pageSize);
        if (groups!=null&&groups.size()>0){
            return new Result(200,"获取成功",groups);
        }
        return new Result(0,"获取失败");
    }

    /**
     * 通过id删除集团
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result deleteGroup(int id,@RequestParam("token") String token){
        if (!checkUtil.checkToken(token)){
            return new Result(0,"没有权限");
        }
        try {
            groupService.deleteGroup(id);
            return new Result(200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(0,"删除失败");
    }

    /**
     * 更新集团信息
     * @param group
     * @param token
     * @return
     */
    @RequestMapping("/update")
    public Result updateGroup(Group group,@RequestParam("token") String token){
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null) {
            try {
                group.setOperate_by(redisUser.getUsername());
                groupService.updateGroup(group);
                return new Result(200, "更新成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(0,"更新失败");
    }
}
