package com.zouni.netty.util;

import com.zouni.netty.base.model.BaseMessage;
import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;

import java.io.UnsupportedEncodingException;

public class FrameUtils {


    public static String dateStr="";


    public static ChatMessage getMessageInfo(byte[] frame){
        ChatMessage headMsg=new ChatMessage();

        if (frame == null || frame.length < 4) {
            return null;
        }
        byte [] msg=ByteUtil.subByte(frame,4,frame.length-4);
        headMsg = ProtobuffUtil.toBean(ProtobuffUtil.fromBytes(msg, ProtoMsg.MessageRequest.class), ChatMessage.class);
        return headMsg;
    };

    public static byte [] getMsgContent(byte [] frame){
        if (frame == null || frame.length < 30) {
            return null;
        }
        int startIndex=30;
        byte msgByte []=new byte[frame.length-startIndex];

        for(int i=startIndex;i<frame.length;i++){
            msgByte[i-startIndex]=frame[i];
        }
        try {

            byte[] content = new byte[frame.length-startIndex];
//            System.arraycopy(frame,startIndex,content,0,content.length);
//            byte [] decrypt =  AESUtil.getInstance().decrypt( content,AESUtil.sKey.getBytes(),AESUtil.ivByte);
            return content;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

        return null;
    };
}
