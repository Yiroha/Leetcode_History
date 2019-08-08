package com.per.iroha.redis;

import com.per.iroha.service.MessageService;
import com.per.iroha.service.impl.MessageServiceImpl;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisMq {

    private static String key = "loginMessage";

    private MessageService messageService = new MessageServiceImpl();
    private Jedis jedis = new Jedis();

    /*
        发送消息
     */
    public void push(String message){
//        System.out.println(redisTemplate);
//        redisTemplate.opsForList().leftPush(key,message);
        jedis.lpush(key,message);
    }

    /*
        获取消息
     */
    public String pop(){
//        return redisTemplate.opsForList().rightPop(key);
        return jedis.rpop(key);
    }

    /*
        清空队列
     */
    public void init(){
//        redisTemplate.opsForList().trim(key,1,0);
        jedis.ltrim(key,1,0);

        //删除在线人数
        jedis.del("Online");
    }
}
