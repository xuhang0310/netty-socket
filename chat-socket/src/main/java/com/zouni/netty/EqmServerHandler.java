package com.zouni.netty;


import com.zouni.netty.attribute.SocketContext;
import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.FrameUtils;
import com.zouni.netty.util.ProtobuffUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class EqmServerHandler extends SimpleChannelInboundHandler<byte[]> {




    private static final Logger logger = LoggerFactory.getLogger(EqmServerHandler.class);


    private DeviceController deviceController;

    /**
     * 通道上下文信息
     */
    private AttributeKey<SocketContext> attrDEqmContext;

    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        logger.warn("channelActive被触发，已经有设备连接上采集软件");
        attrDEqmContext = AttributeKey.valueOf(String.valueOf( UUID.randomUUID()));
        deviceController=new DeviceController(attrDEqmContext);
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] msg) throws Exception {
        //logger.warn("channelRead0进入了");
        ChatMessage chatMessage= FrameUtils.getMessageInfo(msg);
        lossConnectCount=0;
        //System.out.println(chatMessage.getFrom()+"------"+chatMessage.getFromNick());
        if(chatMessage.getMsgType()==ChatMessage.registerMsg){
            deviceController.saveRegisterInfo(channelHandlerContext,chatMessage);
        }else if(chatMessage.getMsgType()==ChatMessage.heartMsg){
            deviceController.heartbeat( channelHandlerContext,chatMessage );
        }else{
            deviceController.otherOperate( channelHandlerContext,chatMessage );
        }
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("断开连接");
        if (StringUtil.isNullOrEmpty(ctx.channel().attr(attrDEqmContext).get().getDeviceNo())) {
            logger.warn("设备注册失败！");
        } else {
            String deviceNo=ctx.channel().attr(attrDEqmContext).get().getDeviceNo();
            SocketContextMap.getInstance().remove(deviceNo);
            logger.warn(ctx.channel().attr(attrDEqmContext).get().getDeviceNo() + " : 编号设备主动断开连接!");
        }
    }
    private int lossConnectCount = 0;

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount++;
                if (lossConnectCount>2){
                    if(ctx!=null&&ctx.channel()!=null&&ctx.channel().attr( attrDEqmContext )!=null&&ctx.channel().attr(attrDEqmContext).get()!=null){
                        String deviceNo=ctx.channel().attr(attrDEqmContext).get().getDeviceNo();
                        SocketContextMap.getInstance().remove(deviceNo);
                        ctx.channel().close();
                    }

                }
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }

    }


}





