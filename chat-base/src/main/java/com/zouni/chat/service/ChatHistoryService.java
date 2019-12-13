package com.zouni.chat.service;

import com.zouni.netty.base.model.ChatMessage;

import java.util.List;

public interface ChatHistoryService {

    public void save(ChatMessage chat);

    public List getList(ChatMessage chatInfo, String startTime);
}
