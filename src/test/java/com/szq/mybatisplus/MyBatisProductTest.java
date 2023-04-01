package com.szq.mybatisplus;

import com.szq.mybatisplus.mapper.ProductMapper;
import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisProductTest {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testProduct01() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格："+productLi.getPrice());
        //小王查询商品价格
        Product productWang= productMapper.selectById(1);
        System.out.println("小李查询的商品价格："+productWang.getPrice());
        //小李商品价格+50
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);
        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice()-30);
        int result = productMapper.updateById(productWang);
        if (result==0){
            //操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }
        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格:"+productLaoban.getPrice());

    }
}
