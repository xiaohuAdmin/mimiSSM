package com.hu.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.Service.ProductInfoService;
import com.hu.mapper.ProductInfoMapper;
import com.hu.pojo.ProductInfo;
import com.hu.pojo.ProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapper pMapper;

    @Override
    public List<ProductInfo> getAll() {
        List<ProductInfo> pList = pMapper.selectByExample(new ProductInfoExample());
        return pList;
    }

    //分页
    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum, pageSize);

        //进行PageInfo的数据封装
        //进行有条件的查询操作,必须要创建ProductInfoExample对象
        ProductInfoExample example = new ProductInfoExample();
        //设置排序,按主键降序
        example.setOrderByClause("p_id desc"); // 相当于 select* from product_info order by p_id desc

        //设置完排序后,取集合,切记切记:一定在取集合之前,设置PageHelper.startPage(pageNum, pageSize);

        List<ProductInfo> pList = pMapper.selectByExample(example); //添加了一个排序的操作

        //将查到的集合进行封装到ProductInfo
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(pList);
        return pageInfo;
    }


    //添加商品
    @Override
    public boolean save(ProductInfo info) {
        boolean flag = true;
        int count = pMapper.insert(info);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public ProductInfo getById(Integer id) {

        return pMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean update(ProductInfo info) {
        boolean flag = true;
        int count = pMapper.updateByPrimaryKey(info);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean delete(int pid) {
        boolean flag = true;
        int count = pMapper.deleteByPrimaryKey(pid);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean deleteAll(String[] ids) {
        boolean flag = true;
        int count = pMapper.deleteAllById(ids);
        if (count != ids.length) {
            flag = false;
        }
        return flag;
    }
}