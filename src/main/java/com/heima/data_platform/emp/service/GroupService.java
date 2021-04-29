package com.heima.data_platform.emp.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.data_platform.emp.common.Group;
import com.heima.data_platform.emp.dao.EnterpriseMapper;
import com.heima.data_platform.emp.dao.GroupMapper;
import com.heima.data_platform.nb.common.Meter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    final EnterpriseMapper enterpriseMapper;
    @Autowired
    public GroupService(GroupMapper groupMapper, EnterpriseMapper enterpriseMapper) {
        this.groupMapper = groupMapper;
        this.enterpriseMapper = enterpriseMapper;
    }

    /**
     * 添加集团信息
     * @param group group
     * @return void
     */
    public void addGroup(Group group){
        String now = DateUtil.now();
        log.info("now:"+now);
        group.setCreate_time(now);
        group.setUpdate_time(now);
        group.setEnable(true);
        group.setDeleted(false);
        groupMapper.addGroup(group);
    }

    /**
     * 获取所有集团
     * @return list
     */
    public PageInfo<Group> getGroup(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Group> groups = groupMapper.getGroup();
        PageInfo<Group> pageInfo = new PageInfo<Group>(groups);
        return pageInfo;
    }

    /**
     * 删除集团
     * @param id id
     */
    public void deleteGroup(int id) {
        int i = groupMapper.changeDelete(id);
        log.info("有{}条数据被更改",i);
        //删除集团下属公司
        List<Integer> entIds = enterpriseMapper.getEntIds(id);
        for (Integer entId : entIds) {
            enterpriseMapper.changeDelete(entId);
        }
    }

    /**
     * 更新集团信息
     * @param group group
     */
    public void updateGroup(Group group) {
        String now = DateUtil.now();
        group.setUpdate_time(now);
        groupMapper.updateGroup(group);
    }
}
