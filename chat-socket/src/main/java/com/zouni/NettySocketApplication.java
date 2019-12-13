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

        StringRedisTemplate redisTemplate=SpringApplicationContextUtil.getApplicationContext().getBean(StringRedisTemplate.class);
        redisTemplate.opsForValue().set("11","33");
        String abc=(String)redisTemplate.opsForValue().get("11");
        System.out.println(abc);

        ChatService chat=run.getBean(ChatService.class);
       // chat.register(new ChatMessage());
    }
}
