package com.heima.data_platform.emp.dao;

import com.heima.data_platform.emp.common.Enterprise;
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
    @Insert("insert into emp_ent (group_id, enterprise, addr, director, phone, simple_name, create_time, operate_by, update_time, enable,deleted) " +
            "VALUES ( #{group_id} ,#{enterprise}, #{addr}, #{director} ,#{phone} ,#{simple_name} ,#{create_time} ,#{operate_by} ,#{update_time} ,#{enable},#{deleted} ) ")
    void addEnterprise(Enterprise enterprise);

    /**
     * 查询企业
     * @return
     */
    //@Select("select id, group_id, enterprise, addr, director, phone, simple_name, create_time, operate_by, update_time, enable from emp_ent where deleted = 0")
    List<Enterprise> getEnterprise(Enterprise enterprise);

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

    /**
     * 查询集团下企业
     * @param id
     * @return
     */
    @Select("select id from emp_ent where group_id = #{id} ")
    List<Integer> getEntIds(int id);

    @Select("select name from emp_group WHERE id = (SELECT group_id FROM emp_ent WHERE id = #{id} )")
    String getGroupNameByEntId(Integer id);
}
