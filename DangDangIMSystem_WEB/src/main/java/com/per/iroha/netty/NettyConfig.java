package com.per.iroha.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyConfig {

    public static String NETTY_HOST = "127.0.0.1";
    public static int NETTY_PORT = 3085;
    public static ChannelGroup globalChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
