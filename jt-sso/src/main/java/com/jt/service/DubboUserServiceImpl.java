package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.UUID;

@Service
public class DubboUserServiceImpl implements DubboUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;
    @Transactional
    @Override
    public void saveUser(User user) {
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass)
                .setEmail(user.getPhone())
                .setCreated(new Date())
                .setUpdated(user.getCreated());
        userMapper.insert(user);
    }

    @Override
    public String findUserByUP(User user) {
        String ticket = null;
        //为了与数据库数据一致,需要将密码加密
        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pass);
        QueryWrapper<User> queryWrapper =
                new QueryWrapper<User>(user);
        //根据用户名和密码校验数据
        User userDB = userMapper.selectOne(queryWrapper);
        if (userDB != null) {
            //将数据库数据转化为json保存到redis中
            String uuid = UUID.randomUUID().toString();
            ticket = DigestUtils.md5DigestAsHex(uuid.getBytes());
            //进行脱敏处理   100xxx0311
            userDB.setPassword("123456你信吗??");
            String userJSON =
                    ObjectMapperUtil.toJSON(userDB);
            jedisCluster.setex(ticket, 7 * 24 * 3600, userJSON);
        }
        return ticket;
    }
}
