package com.zouni.chat.service.impl;

import com.zouni.chat.service.ChatHistoryService;
import com.zouni.netty.base.model.ChatMessage;
import com.zouni.redis.RedisHelper;
import com.zouni.util.CalendarUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    public static final String chatHistoryKey="chat_history";
    @Override
    public void save(ChatMessage chat) {
        String key=getChatHistoryRedisKey(chat,null);
        boolean bool= RedisHelper.Instance().IsDataValid(key);
        if(!bool){
            List list=new ArrayList();
            list.add(chat);
            RedisHelper.Instance().SetList(getChatHistoryRedisKey(chat,null), list);
            RedisHelper.Instance().SetKeyValid(getChatHistoryRedisKey(chat,null),true,30, TimeUnit.DAYS);
        }else{
            RedisHelper.Instance().PushList(getChatHistoryRedisKey(chat,null), chat);
        }
    }

    @Override
    public List getList(ChatMessage chatInfo, String startTime) {
        String key=getChatHistoryRedisKey(chatInfo,startTime);
        boolean bool= RedisHelper.Instance().IsDataValid(key);
        if(bool){
            List list= RedisHelper.Instance().GetListByKey(key);
            return list;
        }
        return null;
    }


    public static String getChatHistoryRedisKey(ChatMessage chatInfo,String time){
        if(time==null){
            Calendar calendar=Calendar.getInstance();
            time= CalendarUtils.convertStrThree(calendar);
        }

        return chatHistoryKey+(chatInfo.getFrom()+chatInfo.getTo())+ time;
    }
}
