package com.hu.Service;

import com.github.pagehelper.PageInfo;
import com.hu.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    //显示全部商品(不分页)
    List<ProductInfo> getAll();

    //分页
    PageInfo splitPage(int pageNum,int pageSize);

    boolean save(ProductInfo info);

    //按主键id查询
    ProductInfo getById(Integer id);

    //更新商品update
    boolean update(ProductInfo info);

    //单个删除商品
    boolean delete(int pid);
    //批量删除
    boolean deleteAll(String[] ids);
}
