package com.per.iroha.netty;

import com.per.iroha.redis.RedisMq;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class NettyServerBootStrap {

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;

    @Autowired
    private RedisMq redisMq;

    public ChannelFuture start(InetSocketAddress address){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyHandlerInitializer())
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        ChannelFuture future = bootstrap.bind(address).syncUninterruptibly();
        channel = future.channel();

        redisMq.init();
        return future;
    }

    public void destroy(){
        if(channel != null){
            channel.close();
        }
        NettyConfig.globalChannels.close();
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
