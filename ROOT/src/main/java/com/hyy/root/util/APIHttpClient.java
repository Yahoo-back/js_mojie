package com.hyy.root.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class APIHttpClient {

	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
	
	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url 请求的url地址 ?之前的地址
	 * @param json  请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url,String json) {
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
			System.out.println("result=[" + result + "]");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    @SuppressWarnings("deprecation")
    public static String postForJson(String msg)
    {
        String content = null;
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 15000);
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        
        HttpPost postMethod = new HttpPost("http://credit.hokagecloud.com/mchapi/jxl/addBasicInfo");
        try
        {
            // 从接过过来的代码转换为UTF-8的编码
            HttpEntity stringEntity = new StringEntity(msg, "application/json", "UTF-8");
            postMethod.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(postMethod);
            HttpEntity entity = response.getEntity();
            
            if (entity != null)
            {
                // 使用EntityUtils的toString方法，传递默认编码，在EntityUtils中的默认编码是ISO-8859-1
                content = EntityUtils.toString(entity, "UTF-8");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            postMethod.abort();
            httpClient.getConnectionManager().closeExpiredConnections();
            httpClient.getConnectionManager().shutdown();
            httpClient.close();
        }
        return content;
    }
    
    public static void main(String[] args) {
		/*String url = "https://api.submail.cn/message/multixsend.json";
		JSONObject detail = new JSONObject();
		detail.put("appid", "11821");
		detail.put("to", "18913963701");
		detail.put("project", "xyoSt2");
		detail.put("timestamp", "");
		detail.put("sign_type", "");
		detail.put("signature", "c022f2310393fd9d2624fb7a442d4907");
		
		
		JSONArray arry = new JSONArray();
		JSONObject detail1 = new JSONObject();
		detail1.put("to", "18913963701");
		JSONObject detail2 = new JSONObject();
		detail2.put("rund", "6661群发成功");
		detail1.put("vars", detail2);
		arry.add(detail1);
		
		detail1 = new JSONObject();
		detail1.put("to", "15951892570");
		detail2 = new JSONObject();
		detail2.put("rund", "6662群发成功！");
		detail1.put("vars", detail2);
		arry.add(detail1);
		
		detail.put("multi", arry.toJSONString());
		System.out.println(detail.toJSONString());
		//doPost(url, detail.toJSONString());*/ 
    	String url = "https://api.submail.cn/message/multixsend.json";
    	ArrayList<String> tophones=new ArrayList<String>();
    	tophones.add("18913963701");
    	//tophones.add("17712288886");
    	String content="0000测试中文";
    	String signature="7c7cb55012d5c52dbf2dda5a97f39f4e";
    	String project="U58t62";
    	String apprid="11864";
    	smspush( tophones , content, url, signature, project, apprid);
    			
	}
    //短信群发
    public static String smspush(ArrayList<String> tophones ,String content,String url,String signature,String project,String apprid){
    	JSONObject detail = new JSONObject();
    	detail.put("appid", apprid);
    	detail.put("project", project);
    	detail.put("signature", signature);
    	JSONArray arry = new JSONArray();
    	JSONObject detail1 = new JSONObject();
    	JSONObject detail2 = new JSONObject();
    	for (int i=0;i<tophones.size();i++) {
    		detail1 = new JSONObject();
    		detail2 = new JSONObject();
    		detail1.put("to", tophones.get(i));
    		detail2.put("stu_name", content);
    		detail1.put("vars", detail2);
    		arry.add(detail1);
		}
    	detail.put("multi", arry.toJSONString());
    	String result=doPost(url, detail.toJSONString());
    	return result;
    }

}
