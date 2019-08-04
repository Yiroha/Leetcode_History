package com.per.iroha.netty;

import com.alibaba.fastjson.JSON;
import com.per.iroha.model.Group;
import com.per.iroha.model.WebSocketMessage;
import com.per.iroha.service.MessageService;
import com.per.iroha.service.impl.MessageServiceImpl;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;

public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    private MessageService messageService = new MessageServiceImpl();

    private int lastId = 0;

    private String lastGroup = "";

    // 集中处理 ws 中的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 文本消息
        WebSocketMessage webSocketMessage = JSON.parseObject(msg.text(),WebSocketMessage.class);
        webSocketMessage.setFromUsername(messageService.getSession(ctx.channel()).getRealName());
        webSocketMessage.setFromUserId(messageService.getSession(ctx.channel()).getUserId());

        logger.info("接收到消息：" + webSocketMessage);
        switch (webSocketMessage.getType()){
            case 1: //广播通知
                if(webSocketMessage.getMessage() != null){
                    NettyConfig.globalChannels.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(webSocketMessage)));
                    messageService.saveAdvice(webSocketMessage);
                    ctx.channel().eventLoop().execute(new Runnable() {
                        @Override
                        public void run() {
                            messageService.saveAdviceToMysql(webSocketMessage);
                        }
                    });
                }else{
                    //历史通知
                    List<String> adviceList = messageService.getAdvice();
                    for (int i = adviceList.size() - 1; i >= 0; i--) {
                        if(adviceList.get(i) == null){
                            break;
                        }
                        WebSocketMessage historyAdvices = JSON.parseObject(adviceList.get(i),WebSocketMessage.class);
                        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(historyAdvices)));
                    }
                }
                break;
            case 2: // 单聊
                setToUser(ctx,webSocketMessage);
                break;
            case 3: //上一次会话
                if(lastId != 0){
                    webSocketMessage.setToUserId(lastId);
                    webSocketMessage.setType(2);
                    sendToUser(ctx,webSocketMessage);
                }else if(!lastGroup.equals("")){
                    webSocketMessage.setGroup(lastGroup);
                    webSocketMessage.setType(5);
                    sendToGroup(lastGroup,ctx,webSocketMessage);
                    messageService.saveGroupHistory(webSocketMessage);
                }else{
                    WebSocketMessage EndMessage = new WebSocketMessage();
                    EndMessage.setErr(1);
                    EndMessage.setMessage("未输入发送对象");
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(EndMessage)));
                }
                break;
            case 4: //创建群组
                ctx.channel().eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        Group group = new Group();
                        group.setGroupName(webSocketMessage.getGroup());
                        if(webSocketMessage.getMessage() != null){
                            String[] users = webSocketMessage.getMessage().split("，");
                            //将在线的用户加入到Set中
                            Set<Integer> userIdSet = new HashSet<>();
                            for(int i = 0; i < users.length; i++) {
                                int userId = messageService.hasUserId(users[i]);
                                if (userId != 0) { //判断缓存中是否有用户Id;
                                    if(messageService.hasLogin(userId)){
                                        userIdSet.add(userId);
                                    }
                                }else{
                                    try {
                                        userId = messageService.getUserId(users[i]);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    if (userId != 0) {
                                        if(messageService.hasLogin(userId)){
                                            userIdSet.add(userId);
                                        }
                                    }
                                }
                            }
                            userIdSet.add(messageService.getSession(ctx.channel()).getUserId());
                            group.setUserList(userIdSet);

                            WebSocketMessage ErrorMessage = new WebSocketMessage();
                            ErrorMessage.setErr(1);
                            if(messageService.createGroup(group)){
                                ErrorMessage.setMessage("创建群组成功，群名：" + group.getGroupName() + " 群成员有： " + messageService.getGroupUser(group.getGroupName()).toString());
                                messageService.getGroup(group.getGroupName()).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(ErrorMessage)));
                            }else{
                                ErrorMessage.setMessage("创建群组失败");
                                ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(ErrorMessage)));
                            }
                        }else{
                            WebSocketMessage ErrorMessage = new WebSocketMessage();
                            ErrorMessage.setErr(1);
                            ErrorMessage.setMessage("群成员有： " + messageService.getGroupUser(group.getGroupName()).toString());
                            messageService.getGroup(group.getGroupName()).writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(ErrorMessage)));
                        }
                    }
                });
                break;
            case 5: //群聊
                String groupName = webSocketMessage.getGroup();
                if(messageService.hasGroup(groupName)){
                    lastGroup = groupName;
                    lastId = 0;
                    if(webSocketMessage.getMessage() != null){
                        sendToGroup(groupName,ctx,webSocketMessage);
                        messageService.saveGroupHistory(webSocketMessage);
                    }else{
                        //获取聊天记录
                        List<String> historyList = messageService.getGroupHistory(groupName);
                        for (int i = historyList.size() - 1; i >= 0; i--) {
                            if(historyList.get(i) == null){
                                break;
                            }
                            WebSocketMessage historyMessage = JSON.parseObject(historyList.get(i),WebSocketMessage.class);
                            ChannelGroup channelGroup = messageService.getGroup(groupName);
                            channelGroup.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(historyMessage)));
                        }
                        WebSocketMessage EndMessage = new WebSocketMessage();
                        EndMessage.setErr(1);
                        EndMessage.setMessage("以上为聊天记录");
                        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(EndMessage)));
                    }
                }else{
                    WebSocketMessage ErrorMessage = new WebSocketMessage();
                    ErrorMessage.setErr(1);
                    ErrorMessage.setMessage("不存在群组");
                    ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(ErrorMessage)));
                }
                break;
        }
    }

    private void setToUser(ChannelHandlerContext ctx ,WebSocketMessage webSocketMessage){
        String username = webSocketMessage.getToUsername();
        int userId = messageService.hasUserId(username);
        if(userId != 0){ //判断缓存中是否有用户Id;
            webSocketMessage.setToUserId(userId);
            lastId = webSocketMessage.getToUserId();
            sendToUser(ctx,webSocketMessage);
        }else{
            ctx.channel().eventLoop().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        int userId = messageService.getUserId(username);
                        if(userId != 0){
                            webSocketMessage.setToUserId(userId);
                            lastId = userId;
                            lastGroup = "";
                            setToUser(ctx,webSocketMessage);
                        }else{
                            //没找到对应的用户
                            WebSocketMessage ErrorMessage = new WebSocketMessage();
                            ErrorMessage.setErr(1);
                            ErrorMessage.setMessage("未找到对应用户");
                            ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(ErrorMessage)));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void sendToUser(ChannelHandlerContext ctx,WebSocketMessage webSocketMessage){
        if(webSocketMessage.getMessage() != null){
            if (messageService.hasLogin(webSocketMessage.getToUserId())){
                Channel channel = messageService.getChannel(webSocketMessage.getToUserId());
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(webSocketMessage)));
            }
            messageService.saveHistory(webSocketMessage);
        }else{
            //获取聊天记录
            List<String> historyList = messageService.getHistory(messageService.getSession(ctx.channel()).getUserId(),webSocketMessage.getToUserId());
            for (int i = historyList.size() - 1; i >= 0; i--) {
                if(historyList.get(i) == null){
                    break;
                }
                WebSocketMessage historyMessage = JSON.parseObject(historyList.get(i),WebSocketMessage.class);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(historyMessage)));
            }
            WebSocketMessage EndMessage = new WebSocketMessage();
            EndMessage.setErr(1);
            EndMessage.setMessage("以上为聊天记录");
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(EndMessage)));
        }
    }

    private void sendToGroup(String groupName,ChannelHandlerContext ctx,WebSocketMessage webSocketMessage){
        ChannelGroup channelGroup = messageService.getGroup(groupName);
        for(Channel channel : channelGroup){
            if(!channel.isActive()){
                channelGroup.remove(channel);
            }else if(channel != ctx.channel()){
                channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(webSocketMessage)));
            }
        }
    }
}
