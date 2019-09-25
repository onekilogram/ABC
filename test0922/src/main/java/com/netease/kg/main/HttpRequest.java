package com.netease.kg.main;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {


    public static final String cookie = "_ga=GA1.2.1658323162.1567153357; _ntes_nnid=5e1cfaa11d5dd0eeed2a54d7e86093db,1567391663568; hb_MA-8993-0A09EA47275D_source=hz.oa.netease.com; mp_MA-91DF-2127272A00D5_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fid-yun.netease.com%2Fget-password%22%2C%22updatedTime%22%3A%201568873284224%2C%22sessionStartTime%22%3A%201568873206288%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%204%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%229c873f81-4686-451d-a713-15968c8df38f%22%2C%22persistedTime%22%3A%201568873206284%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201568873284224%7D%2C%22sessionUuid%22%3A%20%2211741df7-0d5b-4dea-913c-44c506ec382f%22%7D; op_state_id_1.0=qe79s8omfj; op_session_id_1.0=9F8D11C647D4642E745311007CF1D8871AE9D166408D957388B293422762A43E2E9FC65D1E1E920F6B08297CBC375CB25E336FDFD49C390C2851F0E9B156064DB263724C28AF96801703859FAF61807368FA7A7C1469B1CF1035774EBBE4F65C4CDC81D304CB9B45388CAE4FCA788C9186246479805C2356593B19FAF99BE6F0C9208CC5C2E1F29A3400E94982BE26E89BEB9FD375FDEE8B696F2A94E9D09DB117BC9785B4F1EAFEB4DF037B1DCE4C2D4AB6254A86C8E07C9366D7B8438F36D7; CAS_U=CAS_U_ST-23949-dnzQtjA7aJiiNeEsaRT4; JSESSIONID=171977D872648D035505E950B70AC82E";



    public static void main(String[] args) throws Exception {

        String strURL = "http://trace-kl.netease.com/source/search/dependencies/batch?key=com.netease&limit=1000&offset=0";
        Map<String, String> paramMap = new HashMap<String, String>(10);
        paramMap.put("key", "com.netease");
        paramMap.put("limit", "1000");
        paramMap.put("offset", "0");

        OutputStreamWriter out = null;
        InputStream is = null;
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
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println("请求结果返回:" + result);
            }

        } catch (IOException e) {
            e.printStackTrace();
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