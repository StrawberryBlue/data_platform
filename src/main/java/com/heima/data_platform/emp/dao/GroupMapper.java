package com.heima.data_platform.emp.dao;

import com.heima.data_platform.emp.common.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-28 14:11
 * @description：
 * @modified By：wt
 */
@Mapper
@Repository
public interface GroupMapper {
    /**
     * 添加集团
     * @param group
     * @return
     */
    @Insert("insert into emp_group (name, addr, ascription, create_time, operate_by, update_time, enable,deleted) " +
            "values (#{name} ,#{addr} ,#{ascription},#{create_time} ,#{operate_by},#{update_time} ,#{enable},#{deleted} )")
    void addGroup(Group group);

    /**
     * 获取所有
     * @return
     */
    @Select("select id,name, addr, ascription, create_time, operate_by, update_time, enable from emp_group where deleted = 0")
    List<Group> getGroup();

    /**
     * 删除集团
     * @param id
     * @return
     */
    @Update("update emp_group set `deleted` = 1 where id = #{id} ")
    int changeDelete(@Param("id") int id);

    /**
     * 更新集团信息
     * @param group
     */
    void updateGroup(Group group);

}
