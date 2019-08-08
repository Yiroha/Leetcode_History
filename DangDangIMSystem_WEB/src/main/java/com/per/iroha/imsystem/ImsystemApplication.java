package com.per.iroha.imsystem;

import com.per.iroha.netty.NettyConfig;
import com.per.iroha.netty.NettyServerBootStrap;
import io.netty.channel.ChannelFuture;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

import java.net.InetSocketAddress;

@SpringBootApplication
@MapperScan("com.per.iroha.mapper")
@ComponentScan(basePackages = {"com.per.iroha"})
@ServletComponentScan("com.per.iroha.filter")
public class ImsystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ImsystemApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(ImsystemApplication.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(ImsystemApplication.class);

    @Autowired
    private NettyServerBootStrap nettyServerBootStrap;

    @Override
    public void run(String[] args) throws Exception{
        logger.info("Netty server is listen:" + NettyConfig.NETTY_PORT);
        InetSocketAddress address = new InetSocketAddress(NettyConfig.NETTY_HOST,NettyConfig.NETTY_PORT);
        ChannelFuture future = nettyServerBootStrap.start(address);

        Runtime.getRuntime().addShutdownHook(new Thread(()->{ nettyServerBootStrap.destroy();}));

        future.channel().closeFuture().syncUninterruptibly();
    }
}
