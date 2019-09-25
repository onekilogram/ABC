package com.netease.kg.util;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author likeguo
 * @since 2019-09-24
 */
public class UrlPostUtils {

    public static final String cookie = "_ga=GA1.2.1658323162.1567153357; _ntes_nnid=5e1cfaa11d5dd0eeed2a54d7e86093db,1567391663568; hb_MA-8993-0A09EA47275D_source=hz.oa.netease.com; mp_MA-91DF-2127272A00D5_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fid-yun.netease.com%2Fget-password%22%2C%22updatedTime%22%3A%201568873284224%2C%22sessionStartTime%22%3A%201568873206288%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%204%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%229c873f81-4686-451d-a713-15968c8df38f%22%2C%22persistedTime%22%3A%201568873206284%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201568873284224%7D%2C%22sessionUuid%22%3A%20%2211741df7-0d5b-4dea-913c-44c506ec382f%22%7D; JSESSIONID=7DD5970038B0ED556731E1C6F815704A; CAS_U=CAS_U_ST-27519-Xyss3P1YGchcC5PyX7o3";


    public static String getResult(String key,Integer count,Integer offset) throws Exception {

        String strURL = "http://trace-kl.netease.com/source/search/dependencies/batch?key=com.netease&limit=1000&offset=0";
        Map<String, String> paramMap = new HashMap<String, String>(10);
        paramMap.put("key", key);
        paramMap.put("limit", count.toString());
        paramMap.put("offset", offset.toString());

        OutputStreamWriter out = null;
        InputStream is = null;
        String result = "";
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(JSON.toJSONString(paramMap));
            out.flush();
            out.close();

            // 读取响应
            is = connection.getInputStream();
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                result = new String(data, "UTF-8"); // utf-8编码
//                System.out.println("请求结果返回:" + result);

            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return result;
        } finally {
            try {
                is.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
