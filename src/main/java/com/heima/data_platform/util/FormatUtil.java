package com.heima.data_platform.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-18 13:59
 * @description：格式化
 * @modified By：wt
 */
public class FormatUtil {
    /**
     * 日期格式化 把2020-02-02类型转成 2020-02-02 00:00:00
     * @param date
     * @return
     */
    public static String formatDate(String date){
        if(null!=date&date.length()>0){
            return date+" 00:00:00";
        }
        return date;
    }

    public static List<Integer> string2Int(String str, String regex){
        List<Integer> intList = new ArrayList<>();
        String[] split = str.split(regex);
        for (String s : split) {
            int i = Integer.parseInt(s);
            intList.add(i);
        }
        return intList;
    }
}
