package com;

import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.ProtobuffUtil;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {

    private static Log logger = LogFactory.getLog(HelloClientIntHandler.class);

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        logger.info("HelloClientIntHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));
        result.release();
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        logger.info("HelloClientIntHandler.channelActive");
        ChatMessage p = new ChatMessage();
        p.setContent("张三");
        p.setMsgId(30L);
        p.setFrom("xupei0310@163.com");
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
        for(int i=0;i<100;i++){
            ctx.write(msgByte);
            Thread.sleep(100);
        }

        ctx.flush();
    }
}
