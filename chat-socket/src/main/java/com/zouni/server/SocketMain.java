package com.zouni.server;





import com.zouni.netty.NettyServerBootstrap;

import java.util.Date;


public class SocketMain {

    public static  void main(String args []){

        try{

            System.out.println("------测试环境----"+new Date());
            //SocketServiceFactory factory = (SocketServiceFactory) SpringContext.getApplicationContext().getBean( SocketServiceFactory.class );
            NettyServerBootstrap bootstrap=new NettyServerBootstrap( 6789 );

        }catch (Exception e){

        }

    }
}
