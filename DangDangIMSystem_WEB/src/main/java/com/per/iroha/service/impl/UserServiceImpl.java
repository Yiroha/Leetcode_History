package com.per.iroha.service.impl;

import com.per.iroha.mapper.UserMapper;
import com.per.iroha.model.User;
import com.per.iroha.service.UserService;
import com.per.iroha.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    Jedis jedis = new Jedis();

    @Override
    public void register(User user) {
        userMapper.userRegister(user);
    }

    @Override
    public boolean md5Password(User user, int salt) {
        String password = userMapper.findByUsername(user.getUsername()).getPassword();

        String md5 = StringUtils.getMD5Str(password + salt,null);

        return md5.equals(user.getPassword());
    }

    @Override
    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(int userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public boolean hasUser(String username) {
        User user = userMapper.findByUsername(username);
        return user != null;
    }

    //打卡签到
    @Override
    public void checkIn(int userId) {
        Calendar cal = Calendar.getInstance();//得到当前时间
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);//日
//        jedis.set(userId + "checkInTableOf" + month,"0","nx","ex",60 * 60 * 24 * 31);
        jedis.setbit(userId + "checkInTableOf" + month,day,"1");
//        System.out.println(jedis.get(userId + "checkInTableOf" + month));
    }

    @Override
    public long getTable(int userId, int month) {
        return jedis.bitcount(userId + "checkInTableOf" + month);
    }
}
