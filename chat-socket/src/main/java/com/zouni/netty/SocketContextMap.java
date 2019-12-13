package com.zouni.netty;


import com.zouni.netty.attribute.SocketContext;

import java.util.concurrent.ConcurrentHashMap;

public class SocketContextMap {
    // 保存通道信息
    private static ConcurrentHashMap<String, SocketContext> instance = new ConcurrentHashMap<String, SocketContext>();

    public static ConcurrentHashMap<String, SocketContext> getInstance() {
        return instance;
    }

}
