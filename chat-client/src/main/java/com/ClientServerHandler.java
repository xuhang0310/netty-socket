package com;


import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.FrameUtils;
import com.zouni.netty.util.ProtobuffUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class ClientServerHandler extends SimpleChannelInboundHandler<byte[]> {




    private static final Logger logger = LoggerFactory.getLogger(ClientServerHandler.class);






    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        logger.warn("channelActive被触发，已经有设备连接上采集软件");
        logger.info("HelloClientIntHandler.channelActive");
        ChatMessage p = new ChatMessage();
        p.setContent("张三");
        p.setMsgId(30L);
        p.setFrom("sss@163.com");
        p.setTo("kkk");
        p.setFromNick("--");
        p.setUrl("");
        p.setProperty("");
        p.setJson("");
        p.setMsgType(0);
        p.setTime(Calendar.getInstance().getTimeInMillis());

        byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(p, ProtoMsg.MessageRequest.class));
        System.out.println(bytes.length);
        byte [] length= ByteUtil.intByteArray(bytes.length);
        byte [] msgByte=ByteUtil.addBytes(length,bytes);
        ctx.write(msgByte);
        Thread.sleep(100);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] msg) throws Exception {
        //logger.warn("channelRead0进入了");
        System.out.println("-----channelRead0进入了--------");
        ChatMessage chatMessage= FrameUtils.getMessageInfo(msg);
        lossConnectCount=0;
        System.out.println(chatMessage.getFrom());

    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("断开连接");

    }
    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{


    }


}





