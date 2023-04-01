package com.szq.mybatisplus;

import com.szq.mybatisplus.pojo.User;
import com.szq.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusServiceTest {


    @Autowired
    private UserService userService;

    @Test
    void testGet() {
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数:"+ count);
    }

    @Test
    void testInsertMore() {
        //批量添加
        //循环添加进行批量操作
        //INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        List<User> list=new ArrayList<>();
        for (int i=1;i<=10;i++){
            User user = new User();
            user.setName("ybc"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
