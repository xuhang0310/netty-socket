package com.zouni.util;


public class DataParseUtil {
    private static DataParseUtil instance;
    private  DataParseUtil(){

    }
    public static DataParseUtil getInstance(){
        if(instance==null){
            instance = new DataParseUtil();
        }
        return instance;
    }
    public byte[] getHeaderVersion(byte[] data){

        byte []version = new byte[1];
        System.arraycopy(data,0,version,0,version.length);
        return version;
    }
    public byte[] getHeaderAttr(byte[] data){

        byte []version = new byte[1];
        System.arraycopy(data,1,version,0,version.length);
        return version;
    }
    public byte[] getHeaderCmd(byte[] data){

        byte [] content = new byte[2];
        System.arraycopy(data,2,content,0,content.length);
        return content;
    }
    public byte[] getHeaderOther(byte[] data){

        byte [] content = new byte[4];
        System.arraycopy(data,4,content,0,content.length);
        return content;
    }
    public byte[] getHeaderBody(byte[] data){

        byte [] content = new byte[4];
        System.arraycopy(data,8,content,0,content.length);
        return content;
    }

    public byte[] getHeaderVerCode(byte[] data){

        byte [] content = new byte[2];
        System.arraycopy(data,12,content,0,content.length);
        return content;
    }
    public byte[] getHeaderId(byte[] data){

        byte [] content = new byte[16];
        System.arraycopy(data,14,content,0,content.length);
        return content;
    }

}
