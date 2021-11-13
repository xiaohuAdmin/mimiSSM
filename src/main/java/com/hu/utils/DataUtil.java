package com.hu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    /**
     * 将日期格式化
     * @param date 日期
     * @param pattern 格式
     * @return 具有特定格式的字符串日期
     */
    public static String format(Date date,String pattern){

        return  new SimpleDateFormat(pattern).format(date);
    }
}
