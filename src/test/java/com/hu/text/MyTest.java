package com.hu.text;

import com.hu.utils.DataUtil;
import com.hu.utils.FileNameUtil;
import com.hu.utils.MD5Util;
import org.junit.Test;

import java.util.Date;

public class MyTest {
    public static final int PAGE_SIZE=5;
    @Test
    public void testMD5(){
        String str1= MD5Util.getMD5("123");
        System.out.println(str1);

    }

    @Test
    public void myTest01(){
        System.out.println(PAGE_SIZE);
    }

    @Test
    public void myTest02(){

        System.out.println(DataUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));


    }

}
