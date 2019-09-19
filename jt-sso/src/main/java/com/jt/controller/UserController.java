package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping("/check/{parm}/{type}")
    public JSONPObject checkUser(@PathVariable String parm,
                                 @PathVariable Integer type,
                                 String callback) {
        boolean flag = userService.checkUser(parm, type);
        return new JSONPObject(callback, SysResult.success(flag));
    }

    @RequestMapping("/query/{ticket}")
    public JSONPObject findUserByTicket(@PathVariable String ticket,
                                        String callback) {

        String userJSON = jedisCluster.get(ticket);

        return new JSONPObject(callback, SysResult.success(userJSON));
    }
}
