package com.heima.data_platform.emp.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.heima.data_platform.emp.common.Group;
import com.heima.data_platform.emp.dao.GroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：wt
 * @date ：Created in 2021-04-28 14:27
 * @description：
 * @modified By：wt
 */
@Service
@Slf4j
public class GroupService {
    final GroupMapper groupMapper;
    @Autowired
    public GroupService(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public int addGroup(Group group){
        String now = DateUtil.now();
        log.info("now:"+now);
        group.setCreate_time(now);
        group.setUpdate_time(now);
        group.setEnable(true);
        
        return groupMapper.addGroup(group);
    }
}
