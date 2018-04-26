package com.start.boot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * 远程webService调用
 * Created by caomin on 2017/11/8.
 */

@Component
public class WebServiceUtils {

    /**
     *
     * @param url 请求路径
     * @param data 请求参数
     * @return
     */
    public Object get(String url, Map<String, String> data) throws Exception{
        StringBuffer bf = null;
        if (!data.isEmpty()) {
            bf = new StringBuffer("?");
            Set<Map.Entry<String, String>> entries = data.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                bf.append(key+"="+value+"&");
            }
        }
        String paraString = bf.substring(0,bf.length() - 1);
        // 定义HttpClient
        HttpClient client = new DefaultHttpClient();
        // 实例化HTTP方法
        HttpGet request = new HttpGet();
        request.addHeader("Content-Type","text/xml; charset=UTF-8");
        try {
            request.setURI(new URI(url+paraString));
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("["+sdf.format(date)+"] 发送get请求" + request.getURI());
            HttpResponse response = client.execute(request);
            String content = EntityUtils.toString(response.getEntity(),"UTF-8").trim();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("webService远程调用出错",e);
        }
    }
    /**
     * @param url  请求路径
     * @param data 请求参数 ，map
     * @return 返回服务器响应结果
     */
    public Object post(String url, Map<String, String> data) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);
        List<BasicNameValuePair> formparams = new ArrayList();
        if (!data.isEmpty()) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                // 创建参数队列
                String key = entry.getKey();
                String value = entry.getValue();
                formparams.add(new BasicNameValuePair(key, value));
            }
        }

        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + sdf.format(date)+"] 发送post请求" + httppost.getURI());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String resultstr = EntityUtils.toString(entity, "UTF-8");
            return resultstr;
        }
        return null;
    }

    // 解析WebService返回的字符串数组
    public List<String> parseXml(String xml) throws Exception {

        List<String> result = new ArrayList<>();

        InputStream in = new ByteArrayInputStream(xml.getBytes("utf-8"));

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
        NodeList childNodes = db.parse(in).getDocumentElement().getChildNodes(); // 获得根元素下的子节点
        for (int i = 0; i < childNodes.getLength(); i++) // 遍历这些子节点
        {
            Node node = childNodes.item(i);
            if(node.getNodeName().equals("string"))
                result.add(node.getTextContent());
        }

        return result;
    }

}
