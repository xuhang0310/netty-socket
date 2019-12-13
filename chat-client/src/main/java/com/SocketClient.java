package com;

import com.zouni.netty.base.model.ChatMessage;
import com.zouni.netty.protobuf.nano.ProtoMsg;
import com.zouni.netty.util.ByteUtil;
import com.zouni.netty.util.ProtobuffUtil;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;

public class SocketClient {

    public static void main(String args[]) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 6789;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        ChatMessage p = new ChatMessage();
        p.setContent("张三");
        p.setMsgId(30L);
        p.setFrom("xupei0310@163.com");
        p.setTo("kkk");
        p.setFromNick("--");
        p.setUrl("");
        p.setProperty("");
        p.setJson("");
        p.setTime(Calendar.getInstance().getTimeInMillis());
       // byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(p, ProtoMsg.MessageRequest.class));

        byte[] bytes = ProtobuffUtil.toBytes(ProtobuffUtil.toNano(p, ProtoMsg.MessageRequest.class));
        System.out.println(bytes.length);
        byte [] length= ByteUtil.intByteArray(bytes.length);
        //int source=ByteUtil.byteArrayToInt(length);
        byte [] msgByte=ByteUtil.addBytes(length,bytes);

        socket.getOutputStream().write(msgByte);
        outputStream.close();
        socket.close();
    }
}
