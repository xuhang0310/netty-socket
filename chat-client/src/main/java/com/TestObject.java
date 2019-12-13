package com;

import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.ProtobuffUtil;

import java.util.Calendar;

public class TestObject {

    public static void main(String[] args) {
        ChatMessage p = new ChatMessage();
        p.setContent("张三");
        p.setMsgId(30L);
        p.setFrom("xupei0310@163.com");
        p.setTo("kkk");
        p.setFromNick("888888");
        p.setUrl("www.baidu.com");
        p.setProperty("asfdsafdsafvcsvcxzvcxzvcxdafdsafewqrewqrewqrewqrewqgwfgwfwwbwgefdsafdsafdsa");
        p.setJson("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu111111111111qewwwwwwwwwwwwwwwwwsafdsfdsafdsafsafdsafdsafdsafdsafdsafdsafdsafdsafdsaf");
        p.setTime(Calendar.getInstance().getTimeInMillis());
        byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(p, ProtoMsg.MessageRequest.class));
        System.out.println(bytes.length);
       byte [] length= ByteUtil.intByteArray(bytes.length);
       int source=ByteUtil.byteArrayToInt(length);
       byte [] msgByte=ByteUtil.addBytes(length,bytes);


        byte [] msg=ByteUtil.subByte(msgByte,4,msgByte.length-4);
        ChatMessage headMsg = ProtobuffUtil.toBean(ProtobuffUtil.fromBytes(msg, ProtoMsg.MessageRequest.class), ChatMessage.class);
        System.out.println(msgByte.length+"_------"+headMsg.getFromNick());
        short a=32766;
        System.out.println(ByteUtil.short2byte(a).length);
        System.out.println(ByteUtil.byte2short(ByteUtil.short2byte(a)));
    }
}
