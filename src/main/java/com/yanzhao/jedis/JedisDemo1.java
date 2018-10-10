package com.yanzhao.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo1 {
    public static void demo1() {
        // 设置ip地址和端口
        Jedis jedis = new Jedis("192.168.1.3", 6379);
        //保存数据
        jedis.set("name", "yanzhao");
        //获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 释放资源
        jedis.close();
    }

    public static void demo2() {
        // 获得连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最大空闲连接数
        config.setMaxIdle(10);

        // 获得连接池
        JedisPool jedisPool = new JedisPool(config,"192.168.1.3", 6379);

        // 获得核心对象
        Jedis jedis = null;
        try {
            // 通过连接池获得连接
            jedis = jedisPool.getResource();
            // 设置数据
            jedis.set("name", "张三");
            // 获取数据
            String value = jedis.get("name");
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if(jedis != null) {
                jedis.close();
            }
            if(jedisPool != null) {
                jedisPool.close();
            }
        }
    }

    public static void main(String[] args) {
        //JedisDemo1.demo1();
        JedisDemo1.demo2();
    }
}
