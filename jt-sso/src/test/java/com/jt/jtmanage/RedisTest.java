package com.jt.jtmanage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Transaction;

import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;


public class RedisTest {
    private Jedis jedis;

    @Before
    public void init() {
        String host = "192.168.221.129";
        int port = 6379;
        jedis = new Jedis(host, port);
    }

    @Test
    public void testHash() {
        jedis.hset("user", "id", "1001");
        jedis.hset("user", "name", "tomcat猫");
        System.out.println(jedis.hgetAll("user"));

    }

    @Test
    public void testList() {
        jedis.lpush("list","1,2,3,4,5");
        System.out.println(jedis.rpop("list"));
    }

    @Test
    public void testTx() {
        Transaction transaction = jedis.multi();
        try {
            transaction.set("cc","cc");
            transaction.exec();
        } catch (Exception e) {
            transaction.discard();
        }
    }

    @Test
    public void testSentinel() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add("192.168.221.129.26379");
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = pool.getResource();
        jedis.set("1905","哨兵搭建成功");
        System.out.println(jedis.get("1905"));
    }


//    @Test
//    public void testString() {
//        String host = "192.168.221.129";
//        int port = 6379;
//        Jedis jedis = new Jedis(host, port);
//        jedis.set("1705", "redis学习");
//        jedis.setnx("1705", "快完蛋了");
//        System.out.println("获取redis数据:" + jedis.get("1705"));
//    }
}