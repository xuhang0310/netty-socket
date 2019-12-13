package com.zouni.netty.attribute;

import io.netty.channel.ChannelHandlerContext;

public class SocketContext {

    // 设备编号
    private String deviceNo;

    private ChannelHandlerContext deviceCHC;

    private ChannelHandlerContext cmdCHC;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public ChannelHandlerContext getDeviceCHC() {
        return deviceCHC;
    }

    public void setDeviceCHC(ChannelHandlerContext deviceCHC) {
        this.deviceCHC = deviceCHC;
    }

    public ChannelHandlerContext getCmdCHC() {
        return cmdCHC;
    }

    public void setCmdCHC(ChannelHandlerContext cmdCHC) {
        this.cmdCHC = cmdCHC;
    }
}
