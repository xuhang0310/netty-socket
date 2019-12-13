package com.zouni.chat.service;

import com.zouni.netty.attribute.SocketContext;
import com.zouni.netty.base.model.ChatMessage;
import io.netty.channel.ChannelHandlerContext;

public interface ChatService {

    public void register(SocketContext context, ChatMessage message);

    public void singleSend(ChatMessage message, SocketContext context);
}
