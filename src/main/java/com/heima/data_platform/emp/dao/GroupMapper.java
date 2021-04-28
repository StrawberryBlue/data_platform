package com.heima.data_platform.emp.dao;

import com.heima.data_platform.emp.common.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ：wt
 * @date ：Created in 2021-04-28 14:11
 * @description：
 * @modified By：wt
 */
@Mapper
@Repository
public interface GroupMapper {
    @Insert("insert into emp_group (name, addr, ascription, create_time, operate_by, update_time, enable) " +
            "values (#{name} ,#{addr} ,#{ascription},#{create_time} ,#{operate_by},#{update_time} ,#{enable})")
    int addGroup(Group group);
}
