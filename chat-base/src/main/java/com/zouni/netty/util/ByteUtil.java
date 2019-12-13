package com.zouni.netty.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ByteUtil {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }

    /**
     * int 转byte 高位在前,低位在后
     * @param i
     * @return
     */
    public static byte[] intByteArray(int i){
        byte[] result=new byte[4];
        result[0]=(byte)((i >> 24)& 0xFF);
        result[1]=(byte)((i >> 16)& 0xFF);
        result[2]=(byte)((i >> 8)& 0xFF);
        result[3]=(byte)(i & 0xFF);
        return result;
    }

    /**
     *  byte 转int 高位在前,低位在后
     * @param src
     * @return
     */
    public static int byteArrayToInt(byte[] src) {
        if(src.length<4){
            byte [] data=new byte[4];
            int index=3;
            for(int i=src.length-1;i>=0;i--){
                data[index]=src[i];
                index=index-1;
            }
            int value=byteArrayToInt(data);
            return value;
        }else{
            int value;
            value = (int) ( ((src[0] & 0xFF)<<24)
                    |((src[0+1] & 0xFF)<<16)
                    |((src[0+2] & 0xFF)<<8)
                    |(src[0+3] & 0xFF));
            return value;
        }

    }

    public static String getDateStringByByte(byte [] timeByte){
        Date date= getDateByByte( timeByte );
        String dateStr=format.format( date );
        return dateStr;
    };

    public static Date getDateByByte(byte [] timeByte){
        int timestamp= byteArrayToInt( timeByte ) ;
        long timestampLong=((long)timestamp)*1000;
        return  new Date(timestampLong);
    };

    public static byte [] getTimeByteNowTime(){
        long timeLong= new Date().getTime()/1000;
        int dateTime= (int)timeLong;
        byte [] time=intByteArray( dateTime );
        return time;
    };

    public static byte [] getTimeByteByDate(String str){
        try{
            Date date=format.parse( str );
            long timeLong= new Date().getTime()/1000;
            int dateTime= (int)timeLong;
            byte [] time=intByteArray( dateTime );
            return time;
        }catch (Exception e){

        }
        return null;
    };

    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    public static byte[] short2byte(short s){
        byte[] b = new byte[2];
        for(int i = 0; i < 2; i++){
            int offset = 16 - (i+1)*8; //因为byte占4个字节，所以要计算偏移量
            b[i] = (byte)((s >> offset)&0xff); //把16位分为2个8位进行分别存储
        }
        return b;
    }

    public static short byte2short(byte[] b){
        short l = 0;
        for (int i = 0; i < 2; i++) {
            l<<=8; //<<=和我们的 +=是一样的，意思就是 l = l << 8
            l |= (b[i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
        }
        return l;
    }


    public static byte[] long2byte(long res) {
        byte[] buffer = new byte[8];
        for (int i = 0; i < 8; i++) {
            int offset = 64 - (i + 1) * 8;
            buffer[i] = (byte) ((res >> offset) & 0xff);
        }
        return buffer;
    }

    /**
     * 字节数组到long的转换.
     */
    public static long byteArrayToLong(byte[] b){
        long values = 0;
        for (int i = 0; i < 8; i++) {
            values <<= 8; values|= (b[i] & 0xff);
        }
        return values;
    }

    public static byte[] subByte(byte[] b,int off,int length){
        byte[] b1 = new byte[length];
        System.arraycopy(b, off, b1, 0, length);
        return b1;
    }
}
