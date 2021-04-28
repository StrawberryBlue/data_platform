package com.heima.data_platform.nb.dao;

import com.heima.data_platform.nb.common.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @author ：wt
 * @date ：Created in 2021-04-07 9:22
 * @description：用户接口
 * @modified By：wt
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from platform_user where username = #{username} and password = #{password}")
    User getUserInfo(String username, String password);
}
