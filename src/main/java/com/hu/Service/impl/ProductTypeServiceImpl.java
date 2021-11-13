package com.hu.Service.impl;

import com.hu.Service.ProductInfoService;
import com.hu.Service.ProductTypeService;
import com.hu.mapper.ProductTypeMapper;
import com.hu.pojo.ProductType;
import com.hu.pojo.ProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getAll() {
        System.out.println("---------------监听器调用该类 查询所有的商品类型---------------");
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
