package com.jt.jtmanage;

import org.junit.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

public class ShardsTest {
    @Test
    public void testShards() {
        String host = "192.168.38.128";
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(host, 6379));
        shards.add(new JedisShardInfo(host, 6380));
        shards.add(new JedisShardInfo(host, 6381));
        ShardedJedis jedis = new ShardedJedis(shards);
        jedis.set("1905", "分片学习,为集群做准备!!!");
        System.out.println(jedis.get("1905"));
    }
}
