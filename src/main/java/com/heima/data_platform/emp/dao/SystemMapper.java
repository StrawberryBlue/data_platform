package com.heima.data_platform.emp.dao;


import com.heima.data_platform.emp.common.Enterprise;
import com.heima.data_platform.emp.common.System;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 14:50
 * @description： 系统
 * @modified By：wt
 */
@Mapper
@Component
public interface SystemMapper {
    /**
     * 新增system
     * @param system
     */
    @Insert("insert into emp_system (ent_id, name, create_time, operate_by, update_time, mark_name, deleted) " +
            "VALUES ( #{ent_id} ,#{name}, #{create_time}, #{operate_by} ,#{update_time} ,#{mark_name} ,#{deleted} ) ")
    void addSystem(System system);

    /**
     * 获取system
     * @param system
     * @return
     */
    List<System> getSystem(System system);

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update emp_system set `deleted` = 1 where id = #{id} ")
    int changeDelete(@Param("id") int id);

    /**
     * 查询企业下系统id
     * @param id
     * @return
     */
    @Select("select id from emp_system where ent_id = #{id} ")
    List<Integer> getEntIds(int id);


    void updateSystem(System system);
}
