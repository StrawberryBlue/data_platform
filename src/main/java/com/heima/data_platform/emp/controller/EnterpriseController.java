package com.heima.data_platform.emp.controller;

import com.github.pagehelper.PageInfo;
import com.heima.data_platform.common.Result;
import com.heima.data_platform.emp.common.Enterprise;
import com.heima.data_platform.emp.service.EnterpriseService;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 8:55
 * @description： 企业相关
 * @modified By：wt
 */
@Slf4j
@RestController
@RequestMapping("/ent")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    final CheckUtil checkUtil;
    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, CheckUtil checkUtil) {
        this.enterpriseService = enterpriseService;
        this.checkUtil = checkUtil;
    }

    /**
     * 添加企业
     * @param enterprise enterprise
     * @param token token
     * @return
     */
    @RequestMapping("/add")
    public Result addGroup(Enterprise enterprise, @RequestParam String token){
        //log.info("group>>>>>{}+token>>>>>>{}",group,token);
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null){
            try {
                enterprise.setOperate_by(redisUser.getUsername());
                enterpriseService.addEnterprise(enterprise);
                return new Result(200,"添加成功");
            }catch (Exception e){
                e.printStackTrace();
            }
            return new Result(0,"添加失败");
        }
        return new Result(0,"请登录后添加");
    }

    /**
     * 获取所有企业
     * @return result
     */
    @RequestMapping("/get")
    public Result getGroup(Enterprise enterprise,String token,@RequestParam(defaultValue = "1",value = "page") Integer pageNum, @RequestParam(defaultValue = "10",value = "limit") Integer pageSize){
        PageInfo<Enterprise> enterprises = enterpriseService.getEnterprise(pageNum,pageSize,enterprise);
        if (enterprises!=null){
            return new Result(200,"获取成功",enterprises);
        }
        return new Result(0,"获取失败");
    }

    /**
     * 通过id删除企业
     * @param id id
     * @return result
     */
    @RequestMapping("/delete")
    public Result deleteGroup(int id,@RequestParam("token") String token){
        if (!checkUtil.checkToken(token)){
            return new Result(0,"没有权限");
        }
        try {
            enterpriseService.deleteEnterprise(id);
            return new Result(200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(0,"删除失败");
    }

    /**
     * 更新企业信息
     * @param enterprise enterprise
     * @param token token
     * @return result
     */

    @RequestMapping("/update")
    public Result updateGroup(Enterprise enterprise,@RequestParam("token") String token){
        User redisUser = checkUtil.getRedisUser(token);
        if (redisUser!=null) {
            try {
                enterprise.setOperate_by(redisUser.getUsername());
                enterpriseService.updateEnterprise(enterprise);
                return new Result(200, "更新成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Result(0,"更新失败");
    }

}
