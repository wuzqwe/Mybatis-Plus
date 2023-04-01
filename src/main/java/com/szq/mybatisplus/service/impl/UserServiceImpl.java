package com.szq.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szq.mybatisplus.mapper.UserMapper;
import com.szq.mybatisplus.pojo.User;
import com.szq.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
