package com.szq.mybatisplus;

import com.szq.mybatisplus.enums.SexEnum;
import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test() {
        // INSERT INTO t_user ( user_name, age, sex ) VALUES ( ?, ?, ? )
        //admin(String), 33(Integer), 1(Integer
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);

        System.out.println("result:"+result);
    }
}
