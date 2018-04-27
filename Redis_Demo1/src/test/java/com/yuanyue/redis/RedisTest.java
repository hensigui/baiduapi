package com.yuanyue.redis;


import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	@Test
	public void test() {
		Jedis jedis=new Jedis("127.0.0.1",6379); // 创建客户端 设置IP和端口
		jedis.auth("xwtec");
        jedis.set("name", "java知识分享网"); // 设置值
        String value=jedis.get("name"); // 获取值
        System.out.println(value);
        jedis.close(); // 释放连接资源
	}
	
	/**
	 * Jedis连接池
	 */
	@Test
	public void test2() {
		JedisPoolConfig config=new JedisPoolConfig(); // 连接池的配置对象
        config.setMaxTotal(100); // 设置最大连接数
        config.setMaxIdle(10); // 设置最大空闲连接数
         
        JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
         
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource(); // 获取连接
            jedis.auth("xwtec"); // 设置密码
            jedis.set("name", "java知识分享网"); // 设置值
            String value=jedis.get("name"); // 获取值
            System.out.println(value);
             
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
	}

}
