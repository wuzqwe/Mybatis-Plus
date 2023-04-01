package com.szq.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testPage() {
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 LIMIT ?,?
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page, null);
//        System.out.println(page);
        System.out.println("=====================");
        System.out.println(page.getRecords());
        System.out.println(page.getCurrent());//当前页数
        System.out.println(page.getPages());//总页数
        System.out.println(page.getTotal());//总数据
        System.out.println(page.hasNext());//有没有下一页
        System.out.println(page.hasPrevious());//有没有上一页
    }

    @Test
    void testPageVo() {

        Page<User> page=new Page<>(1,3);
        userMapper.selectPageVo(page,20);
        System.out.println(page.getRecords());
        System.out.println(page.getCurrent());//当前页数
        System.out.println(page.getPages());//总页数
        System.out.println(page.getTotal());//总数据
        System.out.println(page.hasNext());//有没有下一页
        System.out.println(page.hasPrevious());//有没有上一页
    }
}
