package com.heima.data_platform.nb.common;

import lombok.Data;

/**
 * @author ：wt
 * @date ：Created in 2021-04-08 9:51
 * @description：温度表模型
 * @modified By：wt
 */
@Data
public class Meter {
    private long id;
    private String deviceId;//设备id
    private String meterNum;//表号
    private String cycle;//通信周期
    private String area;//区域
    private String temp;//温度
    private String wet;//湿度
    private String voltage;//电压
    private String signalNum;//信号
    private String electricity;//电量
    private String isMove;//移动报警
    private String version;//版本号
    private String date;//时间
    private String newCycle;//修改中周期
    private String IMEI;
}
