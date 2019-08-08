package com.per.iroha.netty;

import com.alibaba.fastjson.JSON;
import com.per.iroha.model.User;
import com.per.iroha.model.WebSocketMessage;
import com.per.iroha.redis.RedisMq;
import com.per.iroha.service.MessageService;
import com.per.iroha.service.impl.MessageServiceImpl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class WebSocketHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private MessageService messageService = new MessageServiceImpl();

    private RedisMq redisMq = new RedisMq();

    private WebSocketServerHandshaker handshaker;

    private User user;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
        //WebSocket在一次TCP协议握手的基础上实现的
        handHttpRequest(channelHandlerContext,request);

        //异步把用户信息写入缓存
        channelHandlerContext.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                String userJ = redisMq.pop();
                user = JSON.parseObject(userJ,User.class);
                if(user != null){
                    messageService.bindSession(user,channelHandlerContext.channel());
                }else{
                    Set<Cookie> cookies = ServerCookieDecoder.LAX.decode(request.headers().toString());
                    for(Cookie cookie : cookies){
                        if(cookie.name().equals("userId")){
                            user = messageService.getCookie(cookie.value());
                            break;
                        }
                    }
                    messageService.bindSession(user,channelHandlerContext.channel());
                }
                WebSocketMessage CountMessage = new WebSocketMessage();
                CountMessage.setErr(1);
                CountMessage.setMessage("本月签到次数：" + messageService.getCheckCount(messageService.getSession(channelHandlerContext.channel()).getUserId()));
                channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(CountMessage)));
            }
        });

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当channel连接打开时，加入到总ChannelGroup中
        NettyConfig.globalChannels.add(ctx.channel());

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接时清除用户Session
        messageService.unbindSession(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                logger.info("在规定时间内没有收到客户端的上行数据, 主动断开连接" );
                WebSocketMessage CountMessage = new WebSocketMessage();
                CountMessage.setErr(1);
                CountMessage.setMessage("长时间未进行操作已与服务器断开连接，请刷新~");
                ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(CountMessage)));
                ctx.channel().close();
            }
        }else{
            super.userEventTriggered(ctx, evt);
        }
    }

    // 处理 http 请求，WebSocket 初始握手 (opening handshake ) 都始于一个 HTTP 请求
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request){
        if(!request.decoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade")))){
            sendHttpResponse(ctx, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws://" + NettyConfig.NETTY_HOST + NettyConfig.NETTY_PORT, null, false);
        handshaker = factory.newHandshaker(request);
        if(handshaker == null){
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    // 响应非 WebSocket 初始握手请求
    private void sendHttpResponse(ChannelHandlerContext ctx,  DefaultFullHttpResponse res) {
        if(res.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if(res.status().code() != 200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
