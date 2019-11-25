package com.hyy.ifm.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendImfo {
	
	
	
	public static void main(String strings[]) {
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpPost postMethod = new HttpPost("https://account.chsi.com.cn/passport/login");
			postMethod.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
			postMethod.setHeader("Referer", "http://e.neusoft.edu.cn/nav_login");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", "341122199711040425"));
			params.add(new BasicNameValuePair("password", "turandot1104."));
			params.add(new BasicNameValuePair("Submit", "登录"));
			// 添加参数
			postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// postMethod.setHeaders(arrHeaders);
			HttpResponse response = httpclient.execute(postMethod);
			EntityUtils.consume(response.getEntity());

			class PostThread implements Runnable {
				private HttpClient myclient = null;
				private int num = 23453234;

				public PostThread(HttpClient client) {
					myclient = client;
				}

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						HttpPost postMethod = new HttpPost("http://my.chsi.com.cn/archive/xjarchive.action?trnd=68614926114500587288749763719616");
						postMethod.setHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
						postMethod.setHeader("Referer", "http://e.neusoft.edu.cn/nav_Rcard");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						HttpResponse response;
						try {
							postMethod.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
							response = myclient.execute(postMethod);
							EntityUtils.consume(response.getEntity());
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}
			PostThread mPostThread = new PostThread(httpclient);
			mPostThread.run();

		} catch (IOException e) {

		} finally {

		}
	}
}
