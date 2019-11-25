package com.hyy.ifm.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageToBase64 {
	@SuppressWarnings({ "restriction", "unused" })
    public static String getImageStr(String imgFile) throws Exception {  
        //new一个URL对象  
        URL url = new URL(imgFile);  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);
        //对字节数组Base64编码 
        BASE64Encoder encoder = new BASE64Encoder();
        //Base64编码过的字节数组字符串
        String baseStr=encoder.encode(data);
        //generateImage(baseStr, "E:/1.jpg");
        return baseStr;
    }  
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    
    
    public static boolean generateImage(String imgStr, String path) {
	if (imgStr == null) // 图像数据为空
		return false;
	BASE64Decoder decoder = new BASE64Decoder();
	try {
		byte[] b = decoder.decodeBuffer(imgStr);// Base64解码
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		return true;
	} catch (Exception e) {
		return false;
	}
}
	}
