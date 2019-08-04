package com.per.iroha.service.impl;

import com.alibaba.fastjson.JSON;
import com.per.iroha.mapper.UserMapper;
import com.per.iroha.mapper.UserMapperImpl;
import com.per.iroha.model.Advice;
import com.per.iroha.model.Group;
import com.per.iroha.model.User;
import com.per.iroha.model.WebSocketMessage;
import com.per.iroha.service.MessageService;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageServiceImpl implements MessageService {

    private static Map<Channel,Integer> selfChannelMap = new ConcurrentHashMap<>();

    private static Map<Integer,Channel> userChannelMap = new ConcurrentHashMap<>();
    private static Map<String,ChannelGroup> groupChannelMap = new ConcurrentHashMap<>();

    private Jedis jedis = new Jedis();
    private UserMapperImpl userMapper = new UserMapperImpl();

    private static final String COUNTKEY = "online";

    @Override
    public void bindSession(User user, Channel channel) {

        userChannelMap.put(user.getUserId(),channel);
        selfChannelMap.put(channel,user.getUserId());

        jedis.setbit(COUNTKEY,user.getUserId(),"1");

        jedis.set(user.getUserId().toString(),JSON.toJSONString(user),"nx","ex",60 * 60 * 24);
    }

    @Override
    public void unbindSession(Channel channel) {

        if(selfChannelMap.containsKey(channel)){
            int userId = selfChannelMap.get(channel);

            userChannelMap.remove(userId);
            selfChannelMap.remove(channel);
        }
//        jedis.del(channel.id().asShortText());
    }

    @Override
    public User getSession(Channel channel) {
//        return (User)JSON.parse(jedis.get(channel.id().asShortText()));
        String userId = selfChannelMap.get(channel).toString();
        return JSON.parseObject(jedis.get(userId),User.class);
    }

    @Override
    public User getUserSession(Integer userId) {
        return JSON.parseObject(jedis.get(String.valueOf(userId)),User.class);
    }

    @Override
    public User getCookie(String userId) {
        return JSON.parseObject(jedis.get(userId),User.class);
    }


    @Override
    public boolean hasLogin(int userId) {
        return userChannelMap.containsKey(userId);
    }

    @Override
    public long getOnlineCount() {
        return jedis.bitcount(COUNTKEY);
    }

    @Override
    public long getCheckCount(int userId) {

        Calendar cal = Calendar.getInstance();//得到当前时间
        int month = cal.get(Calendar.MONTH) + 1;
        jedis.bitcount(userId + "checkInTableOf" + month);
        return jedis.bitcount(userId + "checkInTableOf" + month);
    }

    @Override
    public int getUserId(String name) throws SQLException {

        int userId = userMapper.getUserId(name);
        if(userId != 0){
            jedis.set(name,String.valueOf(userId),"nx","ex",60 * 60);
        }
        return userId;
    }

    @Override
    public int hasUserId(String name) {

        if(jedis.exists(name)){
            return Integer.parseInt(jedis.get(name));
        }else{
            return 0;
        }
    }

    @Override
    public Channel getChannel(int userId) {
        return userChannelMap.get(userId);
    }

    @Override
    public void bindChannelGroup(String groupName, ChannelGroup channelGroup) {
        groupChannelMap.put(groupName,channelGroup);
    }

    @Override
    public ChannelGroup getGroup(String groupName) {
        return groupChannelMap.get(groupName);
    }

    @Override
    public Boolean hasGroup(String groupName) {
        return groupChannelMap.containsKey(groupName);
    }

    @Override
    public Boolean createGroup(Group group){

        if(!jedis.exists(group.getGroupName())){
            jedis.set(group.getGroupName(),JSON.toJSONString(group),"nx","ex",60 * 60 * 24);
            ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            for(Integer userId : group.getUserList()){
                channelGroup.add(getChannel(userId));
            }
            bindChannelGroup(group.getGroupName(),channelGroup);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<String> getGroupUser(String groupName) {
        Group group = JSON.parseObject(jedis.get(groupName),Group.class);
        List<String> userList = new ArrayList<>();
        for(Integer userId : group.getUserList()){
            userList.add(getUserSession(userId).getRealName());
        }
        return userList;
    }

//    @Override
//    public void unbindGroup() {
//        for(Map.Entry<String,ChannelGroup> entry : groupChannelMap.entrySet()){
//            String groupName = entry.getKey();
//            jedis.del(groupName);
//            jedis.del(groupName + "History");
//        }
//    }

    @Override
    public void saveHistory(WebSocketMessage webSocketMessage) {
        int user1 = webSocketMessage.getToUserId();
        int user2 = webSocketMessage.getFromUserId();
        if(user1 > user2){
            int temp = user1;
            user1 = user2;
            user2 = temp;
        }
        jedis.lpush(user1 + "And" + user2,JSON.toJSONString(webSocketMessage));
        jedis.expire(user1 + "And" + user2,60 * 60 * 24);
    }

    @Override
    public List<String> getHistory(int user1, int user2) {
        if(user1 > user2){
            int temp = user1;
            user1 = user2;
            user2 = temp;
        }
        return jedis.lrange(user1 + "And" + user2,0,4);
    }

    @Override
    public void saveAdvice(WebSocketMessage webSocketMessage) {
        jedis.lpush("advice",JSON.toJSONString(webSocketMessage));
    }

    @Override
    public void saveAdviceToMysql(WebSocketMessage webSocketMessage) {
        Advice advice = new Advice();
        advice.setDate(webSocketMessage.getDate());
        advice.setFromUsername(webSocketMessage.getFromUsername());
        advice.setAdvice(webSocketMessage.getMessage());

        try {
            userMapper.saveAdvice(advice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAdvice() {
        return jedis.lrange("advice",0,2);
    }

    @Override
    public void saveGroupHistory(WebSocketMessage webSocketMessage) {
        jedis.lpush(webSocketMessage.getGroup() + "History",JSON.toJSONString(webSocketMessage));
        jedis.expire(webSocketMessage.getGroup() + "History",60 * 60 * 24);
    }

    @Override
    public List<String> getGroupHistory(String groupName) {
        return jedis.lrange(groupName + "History",0,4);
    }
}
