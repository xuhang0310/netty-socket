package com.zouni.chat.service;

import com.zouni.netty.attribute.SocketContext;
import com.zouni.netty.base.model.ChatMessage;

public interface ChatService {

    public void register(SocketContext context, ChatMessage message);
}
