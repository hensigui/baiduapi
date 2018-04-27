package com.yuanyue.redis;


import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	@Test
	public void test() {
		Jedis jedis=new Jedis("127.0.0.1",6379); // �����ͻ��� ����IP�Ͷ˿�
		jedis.auth("xwtec");
        jedis.set("name", "java֪ʶ������"); // ����ֵ
        String value=jedis.get("name"); // ��ȡֵ
        System.out.println(value);
        jedis.close(); // �ͷ�������Դ
	}
	
	/**
	 * Jedis���ӳ�
	 */
	@Test
	public void test2() {
		JedisPoolConfig config=new JedisPoolConfig(); // ���ӳص����ö���
        config.setMaxTotal(100); // �������������
        config.setMaxIdle(10); // ����������������
         
        JedisPool jedisPool=new JedisPool(config,"127.0.0.1",6379);
         
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource(); // ��ȡ����
            jedis.auth("xwtec"); // ��������
            jedis.set("name", "java֪ʶ������"); // ����ֵ
            String value=jedis.get("name"); // ��ȡֵ
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
