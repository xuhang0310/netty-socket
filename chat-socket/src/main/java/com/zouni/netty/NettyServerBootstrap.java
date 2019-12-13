package com.zouni.netty;

import com.zouni.netty.codec.SocketDecoder;
import com.zouni.netty.codec.SocketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.TimeUnit;

public class NettyServerBootstrap {

    EventExecutorGroup execGroup = new DefaultEventExecutorGroup(100);


    private static  int port=0;
    private SocketChannel socketChannel;
    public NettyServerBootstrap(int port) throws InterruptedException {
        this.port = port;
        bind();
    }

    private void bind() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker);
        bootstrap.channel( NioServerSocketChannel.class);
        bootstrap.option( ChannelOption.SO_BACKLOG, 128);
        //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        //保持长连接状态
        bootstrap.childOption( ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new SocketDecoder());
                pipeline.addLast(new SocketEncoder());
                pipeline.addLast(  new IdleStateHandler(25, 0, 0, TimeUnit.SECONDS) );
                pipeline.addLast(execGroup,new EqmServerHandler());
            }
        });
        ChannelFuture f= bootstrap.bind(port).sync();
        if(f.isSuccess()){
            System.out.println("server start");
        }
    }
    public static void main(String []args) throws InterruptedException {
        NettyServerBootstrap bootstrap=new NettyServerBootstrap(port);

    }
}
