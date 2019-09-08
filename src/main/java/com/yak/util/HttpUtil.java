package com.yak.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static Map<String, String> doGet(String url, String param) {
        Map<String, String> returnMap = new HashMap<>();

        logger.info("getHttpData:url:" + url + ", param:" + param);
        String strResp;
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient client3 = HttpClients.createDefault();
            HttpResponse resp;
            resp = client3.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                strResp = EntityUtils.toString(resp.getEntity());
                logger.info("the return result:{}", strResp);
            }
            else {
                logger.info("Error Response:", resp.getStatusLine().toString());
            }
        }
        catch (Exception e) {
        }


        return returnMap;
    }

    public void doPost(String url) {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        params.append("name=").append(URLEncoder.encode("&", StandardCharsets.UTF_8));
        params.append("&");
        params.append("age=24");

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url + "?" + params);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
