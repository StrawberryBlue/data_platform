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
    private Integer id;
    private Integer group_id;//集团id
    private String group_name;//集团名
    private String enterprise;//公司名
    private String addr;//地址
    private String director;//总经理
    private String phone;//电话
    private String simple_name;//简写比如黑玛热电简写为HMRD
    private String create_time;
    private String operate_by;
    private String update_time;
    private Boolean enable;//是否启用 默认1启用
    private Boolean deleted;//是否删除 默认0
}
