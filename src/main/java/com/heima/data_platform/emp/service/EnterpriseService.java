package com.heima.data_platform.emp.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.heima.data_platform.emp.common.Enterprise;
import com.heima.data_platform.emp.dao.EnterpriseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 9:29
 * @description：企业
 * @modified By：wt
 */
@Service
@Slf4j
public class EnterpriseService {
    private final EnterpriseMapper enterpriseMapper;
    @Autowired
    public EnterpriseService(EnterpriseMapper enterpriseMapper) {
        this.enterpriseMapper = enterpriseMapper;
    }

    /**
     * 添加企业
     * @param enterprise enterprise
     * @return void
     */
    public void addEnterprise(Enterprise enterprise){
        String now = DateUtil.now();
        enterprise.setCreate_time(now);
        enterprise.setUpdate_time(now);
        enterprise.setEnable(true);
        enterprise.setDeleted(false);
        enterpriseMapper.addEnterprise(enterprise);
    }


    /**
     * 获取所有企业
     * @return list
     */
    public List<Enterprise> getEnterprise(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Enterprise> enterprises = enterpriseMapper.getEnterprise();
        return enterprises;
    }

    /**
     * 删除企业
     * @param id id
     */
    public void deleteEnterprise(int id) {
        int i = enterpriseMapper.changeDelete(id);
        log.info("有{}条数据被更改",i);
    }


    /**
     * 更改企业信息
     * @param enterprise  enterprise
     */
    public void updateEnterprise(Enterprise enterprise) {
        String now = DateUtil.now();
        enterprise.setUpdate_time(now);
        enterpriseMapper.updateEnterprise(enterprise);
    }
}
