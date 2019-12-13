package com.zouni.netty.base.model;

public class ChatMessage {

    public Long  msgId ;
    public String from ;
    public String to;
    public Long time ;
    public int msgType;
    public String content;
    public String url ;
    public String property ;
    public String fromNick ;
    public String json ;

    public static  final int registerMsg=0;
    public static  final int heartMsg=1;
    public static  final int commonMsg=2;
    public static  final int groupMsg=3;
    public static  final int moneyMsg=4;
    public static  final int transferMsg=5;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
