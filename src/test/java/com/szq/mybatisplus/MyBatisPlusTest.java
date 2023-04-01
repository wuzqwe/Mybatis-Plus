package com.szq.mybatisplus;

import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void testInsert() {
        User user=new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@atgui,com");

        //实现用户的新增
        // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("id:"+user.getId());
    }

    @Test
    void testDelete() {
        //通过id来删除用户信息
        //DELETE FROM user WHERE id=?
        int result = userMapper.deleteById(1641705642160824322L);
        System.out.println(result);
    }

    @Test
    void testDelete2() {
        //根据条件删除信息
        //DELETE FROM user WHERE name = ? AND age = ?
        Map<String,Object> map=new HashMap<>();
        map.put("name","Billie");
        map.put("age",24);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:"+result);
    }

    @Test
    void testDelete3() {
        //批量通过多个id实现批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result:"+result);
    }

    @Test
    void testUpdate() {
        //修改用户信息
        //UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@atgui.com");
        int result = userMapper.updateById(user);
        System.out.println("result:"+result);
    }

    @Test
    void testSelect() {
        //通过id来查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    void testSelect2() {
        //根据多个id查询多个用户用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);
    }

    @Test
    void testSelect3() {
        //根据Map集合查询条件
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        Map<String,Object> map=new HashMap<>();
        map.put("name","Jack");
        map.put("age",20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    void testSelectList() {
        //通过条件构造器参询一个List集合，若没有条件，则可以设置null为参数
        //查询所有数据
        //SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @Test
    void testSelect4() {
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
