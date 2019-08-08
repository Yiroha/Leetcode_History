package com.per.iroha.service;

import com.per.iroha.model.Group;
import com.per.iroha.model.User;
import com.per.iroha.model.WebSocketMessage;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {

    //缓存Session
    void bindSession(User user, Channel channel);

    void unbindSession(Channel channel);

    //获取用户缓存信息
    User getSession(Channel channel);

    User getUserSession(Integer userId);

    User getCookie(String userId);

    //判断用户是否在线
    boolean hasLogin(int userId);

    //获取在线人数
    long getOnlineCount();

    //获取本月签到次数
    long getCheckCount(int userId);

    //获取数据库用户Id
    int getUserId(String name) throws SQLException;

    //检查缓存中是否有用户Id
    int hasUserId(String name);

    Channel getChannel(int userId);

    //绑定群组
    void bindChannelGroup(String groupName,ChannelGroup channelGroup);

    ChannelGroup getGroup(String groupName);

    Boolean hasGroup(String groupName);

    Boolean createGroup(Group group);

    List<String> getGroupUser(String groupName);

    void saveHistory(WebSocketMessage webSocketMessage);

    List<String> getHistory(int user1, int user2);

    void saveAdvice(WebSocketMessage webSocketMessage);

    void saveAdviceToMysql(WebSocketMessage webSocketMessage);

    List<String> getAdvice();

    void saveGroupHistory(WebSocketMessage webSocketMessage);

    List<String> getGroupHistory(String groupName);
}
