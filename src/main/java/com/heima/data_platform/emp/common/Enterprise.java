package com.heima.data_platform.emp.common;

import lombok.Data;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 8:47
 * @description： 企业
 * @modified By：wt
 */
@Data
public class Enterprise {
    private int id;
    private int group_id;
    private String enterprise;
    private String addr;
    private String director;
    private String phone;
    private String simple_name;
    private String create_time;
    private String operate_by;
    private String update_time;
    private boolean enable;
    private boolean deleted;
}
