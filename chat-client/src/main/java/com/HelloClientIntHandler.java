package com;

import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.FrameUtils;
import com.zouni.netty.util.ProtobuffUtil;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;

public class HelloClientIntHandler extends SimpleChannelInboundHandler<byte[]> {

    private static Log logger = LogFactory.getLog(HelloClientIntHandler.class);



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) throws Exception {
        //ChatMessage chatMessage= FrameUtils.getMessageInfo(bytes);
        System.out.println(bytes);
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
        ctx.write(msgByte);
        ctx.flush();
    }
}
