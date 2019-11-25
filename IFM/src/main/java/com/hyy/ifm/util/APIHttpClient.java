package com.hyy.ifm.util;

import com.alibaba.fastjson.JSONObject;
import com.visionalsun.util.handler.UtilHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class APIHttpClient {

    private static final CloseableHttpClient httpClient;
    private static final CloseableHttpClient httpClient2;
    private static final int DEFAULT_TIMEOUT = 1000 * 30;
    private static final String CHARSET = "UTF-8";
    private static HttpClient httpClient3;

    private static final String DEFAULT_ENCODEING = "UTF-8";

    /**
     * 获得HttpClient实例
     * 
     * @return
     */
    public static HttpClient getHttpClientInstance() {
        return getHttpClientInstance(DEFAULT_TIMEOUT);
    }

    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(60000).setConnectionRequestTimeout(60000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    static {
        RequestConfig config2 = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).setConnectionRequestTimeout(5000).build();
        httpClient2 = HttpClientBuilder.create().setDefaultRequestConfig(config2).build();
    }

    /**
     * 获得HttpClient实例
     * 
     * @param timeout
     *            超时时间（单位秒）
     * @return
     */
    public static HttpClient getHttpClientInstance(int timeout) {
        if (httpClient3 == null) {
            httpClient3 = new DefaultHttpClient();
            HttpParams httpParams = httpClient.getParams();
            HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(httpParams, DEFAULT_ENCODEING);
            if (timeout <= 0) {
                HttpConnectionParams.setConnectionTimeout(httpParams, DEFAULT_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpParams, DEFAULT_TIMEOUT);
            } else {
                HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * timeout);
                HttpConnectionParams.setSoTimeout(httpParams, 1000 * timeout);
            }
            httpParams.setParameter("http.protocol.content-charset", DEFAULT_ENCODEING);
            httpParams.setParameter(HTTP.CONTENT_ENCODING, DEFAULT_ENCODEING);
            httpParams.setParameter(HTTP.CHARSET_PARAM, DEFAULT_ENCODEING);
            httpParams.setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET, DEFAULT_ENCODEING);
        }
        return httpClient3;
    }

    @SuppressWarnings("deprecation")
    public static String postForJson(String url, String msg) {
        String content = null;
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
        HttpConnectionParams.setSoTimeout(httpParams, 60000);
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);

        HttpPost postMethod = new HttpPost(url);
        try {
            System.out.println(msg);
            // 从接过过来的代码转换为UTF-8的编码
            HttpEntity stringEntity = new StringEntity(msg, "application/json", "UTF-8");
            postMethod.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(postMethod);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // 使用EntityUtils的toString方法，传递默认编码，在EntityUtils中的默认编码是ISO-8859-1
                content = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postMethod.abort();
            httpClient.getConnectionManager().closeExpiredConnections();
            httpClient.getConnectionManager().shutdown();
            httpClient.close();
        }
        return content;
    }

    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param json
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public static String doPost(String url, String json) {
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding("utf-8");
            s.setContentType("application/json");
            httpPost.setEntity(s);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            System.out.println("result=[" + result + "]");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
	 * HTTP Post 获取内容
	 * 
	 * @param url 请求的url地址 ?之前的地址
	 * @param json  请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url,JSONObject json) {
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity s = new StringEntity(json.toString(),"utf-8");
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			httpPost.setEntity(s);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, CHARSET);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 发送 get请求
     */
    public void get(String url) {
        try {
            // 设置参数
            // String str = EntityUtils.toString(new
            // UrlEncodedFormEntity(params));
            // httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
            // 创建httpget.
            HttpGet httpget = new HttpGet(url + "?name=aaa&password=bbb");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpClient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Post方式请求URL，获得响应的结果
     * 
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, Object> params) {
        // 创建HttpPost对象，设置HttpPost对象的请求参数
        if (StringUtil.isBlank(url)) {
            return null;
        }
        try {
            HttpClient httpClient = getHttpClientInstance();

            // 创建HttpGet对象，设置HttpPost对象的请求参数、
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> paramList = getNameValuePairs(params);
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, DEFAULT_ENCODEING));

            // 通过HttpClient对象，执行Post请求返回HttpResponse对象
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // 通过HttpResponse对象，获得HttpEntity对象
            HttpEntity httpEntity = getHttpEntity(httpResponse);

            // 通过HttpEntity对象，获得服务器返回的数据（JSON/XML/HTML）
            return getResponseBody(httpEntity, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url, Map<String, Object> params, String encode) {

        try {

            // 创建HttpGet对象，设置HttpPost对象的请求参数
            HttpGet httpGet = null;
            if (!UtilHandler.IsNotBlank(encode)) {
                encode = CHARSET; // 默认UTF-8
            }
            List<NameValuePair> paramList = getNameValuePairs(params);
            if (url.trim().endsWith("?")) {
                httpGet = new HttpGet(url + URLEncodedUtils.format(paramList, encode));
            } else {
                httpGet = new HttpGet(url + "?" + URLEncodedUtils.format(paramList, encode));
            }

            // 通过HttpClient对象，执行Get请求返回HttpResponse对象
            HttpResponse httpResponse = httpClient2.execute(httpGet);

            // 通过HttpResponse对象，获得HttpEntity对象
            HttpEntity httpEntity = getHttpEntity(httpResponse);

            // 通过HttpEntity对象，获得服务器返回的数据（JSON/XML/HTML）
            return getResponseBody(httpEntity, httpResponse);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 将Map参数组装成List<NameValuePair>对象
     * 
     * @param params
     * @return
     */
    private static List<NameValuePair> getNameValuePairs(Map<String, Object> params) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if (params != null) {
            Set<Entry<String, Object>> set = params.entrySet();
            String key = null;
            Object value = null;
            for (Entry<String, Object> entry : set) {
                key = entry.getKey();
                value = params.get(key);
                paramList.add(new BasicNameValuePair(key, (value == null) ? "" : (String.valueOf(value))));
            }
        }
        return paramList;
    }

    /**
     * 通过HttpResponse对象，获得HttpEntity对象
     * 
     * @param httpResponse
     * @return
     */
    private static HttpEntity getHttpEntity(HttpResponse httpResponse) {
        /*
         * logger.debug("请求状态->{}", httpResponse.getStatusLine()); Header
         * headers[] = httpResponse.getAllHeaders(); for (int i = 0; i <
         * headers.length; i++) { logger.debug("{}->{}", headers[i].getName(),
         * httpResponse.getStatusLine()); }
         */
        HttpEntity httpEntity = httpResponse.getEntity();
        /*
         * logger.debug("ContentType->"+ httpEntity.getContentType());
         * logger.debug("ContentEncoding->"+ httpEntity.getContentEncoding());
         * logger.debug("ContentLength->"+ httpEntity.getContentLength());
         */
        return httpEntity;
    }

    /**
     * 通过HttpEntity对象，获得服务器返回的数据（JSON或HTML等）
     * 
     * @param httpEntity
     * @param httpResponse
     * @return
     */
    private static String getResponseBody(HttpEntity httpEntity, HttpResponse httpResponse) {
        String body = null;
        if (httpEntity != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                body = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
                httpEntity.consumeContent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return body;
    }

    /**
     * Http请求
     */
    public static JSONObject doHttpRequest(String url, JSONObject reqJson) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //设置传入参数
        StringEntity entity = new StringEntity(reqJson.toJSONString(), "utf-8");
        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");
        System.out.println(url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            String respContent = EntityUtils.toString(he, "utf-8");
            return (JSONObject) JSONObject.parse(respContent);
        }
        return null;
    }
    
    public static void main(String[] args) {
        String url = "http://192.168.10.145:5000/xuexin";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", "15572566522");
        map.put("password", "112266");
        String a = doGet(url, map, CHARSET);
        System.out.println(a);
    }

}
