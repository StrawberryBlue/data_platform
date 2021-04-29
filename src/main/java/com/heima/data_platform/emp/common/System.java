package com.heima.data_platform.emp.common;

import lombok.Data;

/**
 * @author ：wt
 * @date ：Created in 2021-04-29 14:45
 * @description：
 * @modified By：wt
 */
@Data
public class System {
    private Integer id;
    private Integer ent_id;
    private String ent_name;
    private Integer group_id;
    private String group_name;
    private String name;
    private String create_time;
    private String update_time;
    private String operate_by;
    private String mark_name;
    private boolean deleted;
}
