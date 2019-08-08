package com.per.iroha.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketMessage {

    private int type; // 消息类型
    private int fromUserId; //发送用户Id
    private String fromUsername; // 发送用户名
    private int toUserId; //发送用户Id
    private String toUsername; //接受用户名
    private String group; // 发送群组名
    private String date; //发送日期
    private String time; //发送时间
    private String message; // 消息主体
    private int err; //错误码

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "type=" + type +
                ", fromUserId=" + fromUserId +
                ", fromUsername='" + fromUsername + '\'' +
                ", toUserId=" + toUserId +
                ", toUsername='" + toUsername + '\'' +
                ", group='" + group + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
                ", err=" + err +
                '}';
    }
}
