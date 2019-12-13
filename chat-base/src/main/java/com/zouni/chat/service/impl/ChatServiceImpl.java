package com.zouni.chat.service.impl;

import com.zouni.netty.SocketContextMap;
import com.zouni.netty.attribute.SocketContext;
import com.zouni.chat.service.ChatService;
import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.ProtobuffUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private RedisTemplate redisTemplate;

    public byte [] getCommonMsg(ChatMessage message){
        message.setContent("ok");
        byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(message, ProtoMsg.MessageRequest.class));
        byte [] length= ByteUtil.intByteArray(bytes.length);
        byte [] msgByte=ByteUtil.addBytes(length,bytes);
        return msgByte;
    }
    @Override
    public void register(SocketContext context, ChatMessage message) {
        SocketContextMap.getInstance().put(message.getFrom(), context);
        context.getCmdCHC().writeAndFlush(getCommonMsg(message));
    }
}
