package com.hu.listener;

import com.hu.Service.ProductTypeService;
import com.hu.pojo.ProductType;
import org.omg.IOP.ServiceContextListHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
//监听器
public class ProductTypeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手工的从spring容器当中取出productTypeServiceImpl(所有的商品类型)
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService= (ProductTypeService) context.getBean("productTypeServiceImpl");
        //获取所有的商品类别
        List<ProductType> typeList=productTypeService.getAll();
        //放入全局作用域当中
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
