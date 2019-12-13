package com.zouni.netty;


import com.zouni.netty.attribute.SocketContext;
import com.zouni.netty.base.model.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeviceController {
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    // 通道上下文属性key
    private AttributeKey<SocketContext> attrContext;

    // 通道上下文属性
    private SocketContext socketContext;



    public DeviceController(AttributeKey<SocketContext> attrContext){
        this.attrContext = attrContext;
    }

    public void saveRegisterInfo(ChannelHandlerContext ctx, ChatMessage msg){
        String from=msg.getFrom();
        if(StringUtils.isBlank(from)){
            return;
        }
        logger.info("尝试注册-------设备编号"+from);
        socketContext = new SocketContext();
        socketContext.setDeviceNo(from);
        socketContext.setDeviceCHC(ctx);
        socketContext.setCmdCHC( ctx );
        if(msg!=null){
            ctx.channel().attr(attrContext).set(socketContext);
            ctx.channel().writeAndFlush(msg);
            //将设备编号存入缓存信息中
            SocketContextMap.getInstance().put(from, socketContext);
        }


    }

    /**
     * 处理心跳报文
     * @param ctx
     * @param msg
     */
    public void heartbeat(ChannelHandlerContext ctx, ChatMessage msg) {
        String from = msg.getFrom();
        if (StringUtils.isEmpty(from)) {
            return;
        }
         ctx.channel().writeAndFlush(msg);
    }




    /**
     * 处理其他操作
     * @param ctx
     * @param msg
     */
    public void otherOperate(ChannelHandlerContext ctx,ChatMessage msg){
        String from=msg.getFrom();
        String to=msg.getTo();
        if(StringUtils.isBlank(from)){
            return;
        }
        if(StringUtils.isBlank(to)){
            return;
        }
    }

}
