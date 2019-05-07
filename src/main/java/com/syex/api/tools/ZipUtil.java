package com.syex.api.tools;

import com.alibaba.fastjson.JSONArray;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.GZIPOutputStream;

/**
 * 解压缩字符串工具类
 * @author zhanglinbo 20160827
 *
 */
public class ZipUtil {


    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";


    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    //websocket进行gzip压缩后推送消息
    public final static void gzAndSendWesocket(Session session,Object message) throws EncodeException,IOException{
        /*byte[] bytes = compress(JSONArray.toJSONString(message),GZIP_ENCODE_UTF_8);
        ByteBuffer bf = ByteBuffer.wrap(bytes);
        session.getBasicRemote().sendBinary(bf);*/
        session.getBasicRemote().sendObject(message);
    }
}
