package com.hyy.ifm.util;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientHelper {
  
    public static void main(String[] args) throws Exception {  
        HttpClient client = new HttpClient();  
        //client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT);  
        String userName = "341122199711040425";
        String password = "turandot1104.";
        // 模拟登录页面 login.jsp->main.jsp  
        PostMethod post = new PostMethod("https://account.chsi.com.cn/passport/login");  
        NameValuePair name = new NameValuePair("username", userName);  
        NameValuePair pass = new NameValuePair("password", password);  
        NameValuePair sbx = new NameValuePair("Submit.x", "0");  
        NameValuePair sby = new NameValuePair("Submit.y", "0");  
        NameValuePair sb = new NameValuePair("_eventId", "submit");  
        post.setRequestBody(new NameValuePair[] { name, pass,sbx,sby,sb });  
        int status = client.executeMethod(post);  
          
        BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));    
        StringBuffer stringBuffer = new StringBuffer();    
        String str = "";    
        while((str = reader.readLine())!=null){    
            stringBuffer.append(str);    
        }    
        String ts = stringBuffer.toString();    
          
          
        System.out.println(ts);  
        System.out.println("status=" + status);  
        post.releaseConnection();  
  
        // 查看 cookie 信息  
        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();  
        Cookie[] cookies = cookiespec.match("", 80, "/", false, client.getState().getCookies());  
        if (cookies.length == 0) {  
            System.out.println("None");  
        } else {  
            for (int i = 0; i < cookies.length; i++) {  
                System.out.println(cookies[i].toString());  
            }  
        }  
  
        // 登陆之后访问的页面 main2.jsp  
        PostMethod get = new PostMethod("http://xiaoyuehui-me.com/main/api?cmd=qryLoanList");  
        client.executeMethod(get);  
        System.out.println(get.getResponseBodyAsString());  
        get.releaseConnection();  
    }  
}
