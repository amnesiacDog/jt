package com.jt.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean checkUser(String param, Integer type) {
        String column = 1==type?"username":(2==type?"phone":"email");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column,param);
        int count = userMapper.selectCount(queryWrapper);
        return count>0;
    }
}
