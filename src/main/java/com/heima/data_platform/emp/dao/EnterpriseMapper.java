package com.heima.data_platform.emp.dao;

import com.heima.data_platform.emp.common.Enterprise;
import com.heima.data_platform.emp.common.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 8:56
 * @description：企业
 * @modified By：wt
 */
@Mapper
@Repository
public interface EnterpriseMapper {
    /**
     * 新增企业
     * @param enterprise
     */
    @Insert("insert into emp_ent (group_id, enterprise, addr, director, phone, simple_name, create_time, operate_by, update_time, enable) " +
            "VALUES ( #{group_id} ,#{enterprise}, #{addr}, #{director} ,#{phone} ,#{simple_name} ,#{create_time} ,#{operate_by} ,#{update_time} ,#{enable}  ) ")
    void addEnterprise(Enterprise enterprise);

    /**
     * 查询企业
     * @return
     */
    @Select("select id, group_id, enterprise, addr, director, phone, simple_name, create_time, operate_by, update_time, enable from emp_ent where deleted = 0")
    List<Enterprise> getEnterprise();

    /**
     * 删除
     * @param id
     * @return
     */
    @Update("update emp_ent set `deleted` = 1 where id = #{id} ")
    int changeDelete(@Param("id") int id);

    /**
     * 更新
     * @param enterprise
     */
    void updateEnterprise(Enterprise enterprise);
}
