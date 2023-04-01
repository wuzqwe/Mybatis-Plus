package com.szq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test01() {
        //查询用户名包含a,年龄在20到30之间，邮箱信息不为null的用户信息
        //条件构造器
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","b")
                        .between("age",20,30)
                                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test02() {
        //查询用户信息，按照年龄降序排序，若年龄相同，则按照id升序排序
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test03() {
        //删除邮箱地址为null的用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test04() {
        ////将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        // UPDATE t_user SET user_name=?, email=? WHERE is_delete=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",20)
                        .like("user_name","a")
                        .or()
                        .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("test@szq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test05() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //lambda中的条件表达式中优先执行
        //UPDATE t_user SET user_name=?, email=? WHERE is_delete=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("test@szq.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test06() {
        //SELECT user_name,age,email FROM t_user WHERE is_delete=0
        //查询用户的用户名、年龄、邮箱信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test07() {
        //查询id小于等于100的用户信息
        //子查询
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (uid IN (select uid from t_user where uid <=100))
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid <=100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test08() {
        //将用户名中包含a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE t_user SET user_name=?,email=? WHERE is_delete=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        updateWrapper.set("user_name","小黑").set("email","szq@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result:"+result);
    }

    @Test
    void test09() {
        String username="a";
        Integer ageBegin=null;
        Integer ageEnd=30;
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (age >= ? AND age <= ?)
        // SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (user_name LIKE ? AND age <= ?)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            //isNotBlank判断某个字符串是否不为空字符串，不为null，不为空白符
            queryWrapper.like("user_name",username);
        }
        if (ageBegin!=null){
            queryWrapper.ge("age",ageBegin);
        }
        if (ageEnd!=null){
            queryWrapper.le("age",ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test10() {
        String username="a";
        Integer ageBegin=null;
        Integer ageEnd=30;
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (user_name LIKE ? AND age <= ?)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }


    @Test
    void test11() {
        //SELECT uid AS id,user_name AS name,age,email,is_delete FROM t_user WHERE is_delete=0 AND (user_name LIKE ? AND age <= ?)
        String username="a";
        Integer ageBegin=null;
        Integer ageEnd=30;
        //User::getName 字段名 user_name
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void test12() {
        //将用户名中包含a并且（年龄大于20或邮箱为null）的用户信息修改
        //UPDATE t_user SET user_name=?,email=? WHERE is_delete=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName,"a")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName,"小黑").set(User::getEmail,"szq@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result:"+result);
    }
}
