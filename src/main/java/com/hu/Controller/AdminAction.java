package com.hu.Controller;

import com.hu.Service.AdminService;
import com.hu.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    private AdminService adminService;
    //实现登录判断,跳转
    @RequestMapping("login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin =adminService.login(name,pwd);
        if (admin!=null){
            request.setAttribute("admin",admin);
            return "main";

        }else {
            request.setAttribute("errmsg","用户名或密码不正确!");
            return "login";
        }

    }


}
