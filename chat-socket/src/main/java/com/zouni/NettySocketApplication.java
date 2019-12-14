package com.zouni;

import com.zouni.chat.service.ChatService;
import com.zouni.netty.NettyServerBootstrap;
import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.util.SpringApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication(scanBasePackages = "com.zouni.*")
public class NettySocketApplication {


    public static void main(String[] args) throws InterruptedException {
        NettyServerBootstrap bootstrap=new NettyServerBootstrap( 6789 );
        ApplicationContext run =SpringApplication.run(NettySocketApplication.class,args);
        SpringApplicationContextUtil.setApplicationContext(run);


       // chat.register(new ChatMessage());
    }
}
