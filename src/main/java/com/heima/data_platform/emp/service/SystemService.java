package com.heima.data_platform.emp.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.data_platform.emp.common.System;
import com.heima.data_platform.emp.dao.SystemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 15:09
 * @description：
 * @modified By：wt
 */
@Slf4j
@Service
public class SystemService {
    final SystemMapper systemMapper;
    final EnterpriseService enterpriseService;
    @Autowired
    public SystemService(SystemMapper systemMapper, EnterpriseService enterpriseService) {
        this.systemMapper = systemMapper;
        this.enterpriseService = enterpriseService;
    }

    /**
     * 新增系统
     * @param system
     */
    public void addSystem(System system){
        String now = DateUtil.now();
        system.setCreate_time(now);
        system.setUpdate_time(now);
        system.setDeleted(false);
        systemMapper.addSystem(system);
    }

    /**
     * 分页获取system
     * @param pageNum
     * @param pageSize
     * @param system
     * @return
     */
    public PageInfo<System> getSystem(Integer pageNum, Integer pageSize, System system) {
        PageHelper.startPage(pageNum, pageSize);
        List<System> systems = systemMapper.getSystem(system);
        for (System s : systems) {
            Integer ent_id = s.getEnt_id();
            String groupName = enterpriseService.getGroupNameByEntId(ent_id);
            s.setGroup_name(groupName);
        }
        PageInfo<System> pageInfo = new PageInfo<System>(systems);
        return pageInfo;
    }

    /**
     * 删除系统
     * @param id
     */
    public void deleteSystem(int id) {
        int i = systemMapper.changeDelete(id);
        log.info("有{}条数据被更改",i);
    }

    /**
     * 更新系统资料
     * @param system
     */
    public void updateSystem(System system) {
        String now = DateUtil.now();
        system.setUpdate_time(now);

        log.info(">>>>>>>>>>:"+system);
        systemMapper.updateSystem(system);
    }
}
