package com.springapp.mvc.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by zhangjie on 2015/6/9.
 */
public class HttpClientUtil {


    /**
     * 执行get请求
     * @param url 请求的url
     * @param parameters 参数
     * @return 查询结果
     * @throws org.apache.http.client.ClientProtocolException
     * @throws java.io.IOException
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String doGet(String url,
                               Map<String, Object> parameters) throws ClientProtocolException,
            IOException, Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpParams params = new DefaultedHttpParams(httpget.getParams(),
                httpget.getParams());
        Set<Map.Entry<String, Object>> p = parameters.entrySet();
        Iterator<Map.Entry<String, Object>> iter = p.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            params.setParameter(String.valueOf(key), String.valueOf(val));
        }
        httpget.setParams(params);
        HttpResponse response = httpclient.execute(httpget);
        int statusCode = response.getStatusLine().getStatusCode();
        StringBuffer result = new StringBuffer();
        switch (statusCode) {
            case Constant.STATUSCODE_200:
                HttpEntity httpEntity = response.getEntity();
                InputStream iput = httpEntity.getContent();
                BufferedReader ir = new BufferedReader(new InputStreamReader(iput));

                String line = null;
                while ((line = ir.readLine()) != null) {
                    result.append(line);
                }
                break;
            case Constant.STATUSCODE_404:
                throw new Exception("404错误!");
            case Constant.STATUSCODE_500:
                throw new Exception("500错误！");
            case Constant.STATUSCODE_501:
                throw new Exception("501服务器不支持请求的函数！");
            default:
                break;
        }
        return result.toString();
    }
    /**
     * 提交http post 请求
     * @param url  请求的url
     * @param parameters 传递的参数
     * @return json 字符串
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String doPost(String url,
                                Map<String, Object> parameters) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        Set<Map.Entry<String, Object>> p = parameters.entrySet();
        Iterator<Map.Entry<String, Object>> iter = p.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            NameValuePair vp = new BasicNameValuePair(String.valueOf(key),
                    String.valueOf(val));
            params.add(vp);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        StringBuffer result = new StringBuffer();
        switch (statusCode) {
            case Constant.STATUSCODE_200:
                HttpEntity httpEntity = response.getEntity();
                InputStream iput = httpEntity.getContent();
                BufferedReader ir = new BufferedReader(new InputStreamReader(iput));
                String line = null;
                while ((line = ir.readLine()) != null) {
                    result.append(line);
                }
                break;
            case Constant.STATUSCODE_404:
                throw new Exception("404错误!");
            case Constant.STATUSCODE_500:
                throw new Exception("500错误！");
            case Constant.STATUSCODE_501:
                throw new Exception("501服务器不支持请求的函数！");
            default:
                break;
        }
        return result.toString();
    }


    /**
     * 提交http post 请求
     * @param url  请求的url
     * @param parameters 传递的参数
     * @return json 字符串
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String doPostHttps(String url,
                                Map<String, Object> parameters) throws Exception {
        CloseableHttpClient httpclient = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        Set<Map.Entry<String, Object>> p = parameters.entrySet();
        Iterator<Map.Entry<String, Object>> iter = p.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            NameValuePair vp = new BasicNameValuePair(String.valueOf(key),
                    String.valueOf(val));
            params.add(vp);
        }
        httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        StringBuffer result = new StringBuffer();
        switch (statusCode) {
            case Constant.STATUSCODE_200:
                HttpEntity httpEntity = response.getEntity();
                InputStream iput = httpEntity.getContent();
                BufferedReader ir = new BufferedReader(new InputStreamReader(iput));
                String line = null;
                while ((line = ir.readLine()) != null) {
                    result.append(line);
                }
                break;
            case Constant.STATUSCODE_404:
                throw new Exception("404错误!");
            case Constant.STATUSCODE_500:
                throw new Exception("500错误！");
            case Constant.STATUSCODE_501:
                throw new Exception("501服务器不支持请求的函数！");
            default:
                break;
        }
        return result.toString();
    }

    /**
     * 提交http post 请求
     * @param url  请求的url
     * @param json 传递的参数
     * @return json 字符串
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static String doPostHttpsJson(String url,String json) throws Exception {
        CloseableHttpClient httpclient = createSSLClientDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(json, HTTP.UTF_8);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpResponse response = httpclient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        StringBuffer result = new StringBuffer();
        switch (statusCode) {
            case Constant.STATUSCODE_200:
                HttpEntity httpEntity = response.getEntity();
                InputStream iput = httpEntity.getContent();
                BufferedReader ir = new BufferedReader(new InputStreamReader(iput));
                String line = null;
                while ((line = ir.readLine()) != null) {
                    result.append(line);
                }
                break;
            case Constant.STATUSCODE_404:
                throw new Exception("404错误!");
            case Constant.STATUSCODE_500:
                throw new Exception("500错误！");
            case Constant.STATUSCODE_501:
                throw new Exception("501服务器不支持请求的函数！");
            default:
                break;
        }
        return result.toString();
    }

    private static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return  HttpClients.createDefault();
    }
}
