package com.hu.Service.impl;

import com.hu.Service.AdminService;
import com.hu.mapper.AdminMapper;
import com.hu.pojo.Admin;
import com.hu.pojo.AdminExample;
import com.hu.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    //Dao层
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户到DB中查间相应用户对象
        //如果有条件,则一定要创建AdminExample的对象,用来封装条件 就是where后面东西
        AdminExample example=new AdminExample();
        /**
         * 如何添加条件？
         *  select * from Admin where a_name='admin'
         *  我们where后面的条件封装进example里面
         */
        //封装条件
        example.createCriteria().andANameEqualTo(name);

        //调用dao 查询用户 返回List集合的用户
        List<Admin> aList=adminMapper.selectByExample(example);

        //对返回的用户进行判断
        if (aList.size()>0){
            Admin admin=aList.get(0);
            //将页面传入的密码通过md5加密 和 数据库查询出来的密码进行比对
            String miPwd= MD5Util.getMD5(pwd);
            if (miPwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
