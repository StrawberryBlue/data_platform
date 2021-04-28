package com.heima.data_platform.emp.common;

import lombok.Data;

import java.util.Date;

/**
 * @author ：wt
 * @date ：Created in 2021-04-28 14:00
 * @description： 集团
 * @modified By：wt
 */
@Data
public class Group {
    private int id;
    private String name;
    private String addr;
    private String ascription;
    private String create_time;
    private String operate_by;
    private String update_time;
    private boolean enable;
    private boolean deleted;
}
