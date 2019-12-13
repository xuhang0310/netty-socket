package com.zouni.netty.codec;


import com.zouni.netty.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SocketDecoder extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger( SocketDecoder.class);

    /**
     * 编码插件，接受到的数据在此处进行过滤，做拆包粘包处理等操作
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] orignalBytes = new byte[in.readableBytes()];
        in.getBytes(in.readerIndex(), orignalBytes);
        if (in.readableBytes() <= 0) {
            return;
        }

        /**消息完整性校验开始**/
        int length= ByteUtil.byteArrayToInt(ByteUtil.subByte(orignalBytes,0,4));
        int realLength=orignalBytes.length;
        logger.info( "消息实际长度---"+realLength+"-----------消息声明长度------"+length );
        if(realLength<length){
            logger.warn("数据包缺失");
            return;
        }

        logger.warn("服务端接收程序：" );

        // TODO 做粘包处理

        byte[] bytes = new byte[in.readableBytes()];

        in.readBytes(bytes, 0, bytes.length);
        out.add(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("unexpected exception", cause);
        // ctx.close();
    }
}
