package com.zouni.netty.codec;



import com.zouni.netty.util.ChannelHandlerContextUtils;
import com.zouni.netty.util.FrameUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName: SocketResponseEncoder
 * @Function: 消息编码
 * @date: May 22, 2017 10:49:11 AM
 * 
 * @author Joker
 * @version
 * @since JDK 1.8
 */
//@Sharable
public class SocketEncoder extends ByteArrayEncoder
{
	 private static final Logger logger = LoggerFactory.getLogger( SocketEncoder.class.getName());

	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception
	{
		//logger.warn("服务器发送数据：" + FrameUtils.toString(msg));
		ChannelHandlerContextUtils.writeAndFlush(ctx, msg);
	}
}
