package com.heima.data_platform.nb.common;

import lombok.Data;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 9:18
 * @description：用户实体类
 * @modified By：wt
 */
@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String mark;
    private String address;
    private String module;
    private List<Integer> moduleList;
    private String token;
}
