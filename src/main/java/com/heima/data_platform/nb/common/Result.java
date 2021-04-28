package com.heima.data_platform.nb.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：wt
 * @date ：Created in 2021-04-07 14:27
 * @description：返回值
 * @modified By：wt
 */
@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String message;
    private Object data;


    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }
}
