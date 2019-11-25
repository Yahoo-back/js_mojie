package com.hyy.ifm.util;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String nvl(Object obj) {
		String str = "";
		if (null != obj)
			return obj.toString();
		return str;
	}

	public static String keepPointBy2(Double d) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(d);
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 格式化数字为千分位显示；
	 * 
	 * @param 要格式化的数字；
	 * @return
	 */
	public static String fmtMicrometer(Object obj) {
		String text = nvl(obj);
		DecimalFormat df = null;
		if (text.indexOf(".") > 0) {
			if (text.length() - text.indexOf(".") - 1 == 0) {
				df = new DecimalFormat("###,##0.");
			} else if (text.length() - text.indexOf(".") - 1 == 1) {
				df = new DecimalFormat("###,##0.0");
			} else {
				df = new DecimalFormat("###,##0.00");
			}
		} else {
			df = new DecimalFormat("###,##0");
		}
		double number = 0.0;
		try {
			number = Double.parseDouble(text);
		} catch (Exception e) {
			number = 0.0;
		}
		return df.format(number);
	}

	public static int parseInt(Object obj) {
		try {
			if (null != obj) {
				return Integer.parseInt(nvl(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static double parseDouble(double f) {
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static Double getMonthPay(String applyAmt, String ylx, int qx) {
		Double pay = 0.0;
		pay = (Double.valueOf(applyAmt) + Double.valueOf(applyAmt) * Double.valueOf(ylx) * qx) / qx;
		return parseDouble(pay);
	}

	private static int count = 1;
	private static Map<String, String> countMap = new HashMap<String, String>();

	public static synchronized String getSeri(String curdate) {
		if (null == countMap.get(curdate)) {
			count = 1;
			countMap.put(curdate, "001");
		} else {
			if (count >= 10) {
				countMap.put(curdate, "0" + count);
			} else if (count >= 100) {
				countMap.put(curdate, count + "");
			} else {
				countMap.put(curdate, "00" + count);
			}
		}
		count++;
		return curdate + countMap.get(curdate);
	}

	public static String zero(int length, String curdate) {
		if (!curdate.equals("")) {
			int l = curdate.length();
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < length - l; i++) {
				s.append("0");
			}
			s.append(curdate);
			return s.toString();
		} else {
			return "";
		}
	}

	public static int getCardNoYear(String cardNo) {
		return parseInt(cardNo.substring(6, 10));
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static String md5Sign(Map<String, Object> sArray, boolean isEncode, String privateKey) {
		return MD5(createLinkStringNoNull(sArray, isEncode, privateKey)).toLowerCase();
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @param isEncode
	 *            是否要对value进行URLEncoder
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, Object> params, boolean isEncode) {
		params.remove("attchs");
		params.remove("cnts");
		params.remove("ships");
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";
		try {
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = nvl(params.get(key));
				if (isEncode) {
					if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + java.net.URLEncoder.encode(value, "utf-8");
					} else {
						prestr = prestr + key + "=" + java.net.URLEncoder.encode(value, "utf-8") + "&";
					}
				} else {
					if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + value;
					} else {
						prestr = prestr + key + "=" + value + "&";
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return prestr;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @param isEncode
	 *            是否要对value进行URLEncoder
	 * @return 拼接后字符串
	 */
	public static String createLinkStringNoNull(Map<String, Object> params, boolean isEncode, String privateKey) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";
		try {
			for (int i = 0; i < keys.size(); i++) {
				String key = keys.get(i);
				String value = nvl(params.get(key));
				/*
				 * if("".equals(value)){ continue; }
				 */
				if (isEncode) {
					if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + java.net.URLEncoder.encode(value, "utf-8");
					} else {
						prestr = prestr + key + "=" + java.net.URLEncoder.encode(value, "utf-8") + "&";
					}
				} else {
					if (value.indexOf("&") > 0 || value.indexOf("@") > 0) {
						value = java.net.URLEncoder.encode(value, "utf-8");
					}
					if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
						prestr = prestr + key + "=" + value;
					} else {
						prestr = prestr + key + "=" + value + "&";
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return prestr + privateKey;
	}

	/**
	 * 将字符串转换为Double，转换失败返回null
	 */
	public static Double parseDouble(String str) {
		return StringUtil.parseDouble(str, null);
	}

	/**
	 * 将字符串转换为Double，转换失败返回null
	 */
	public static Double parseDouble4Object(Object o) {
		return StringUtil.parseDouble(nvl(o), null);
	}

	/**
	 * 将字符串转换为Double，转换失败返回defaultValue
	 */
	public static Double parseDouble(String str, Double defaultValue) {
		if (str == null)
			return null;
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {

		}
		return defaultValue;
	}

	/**
	 * 将字符串转换为Integer，转换失败返回null
	 */
	public static Integer parseInteger(String str) {
		return StringUtil.parseInteger(str, null);
	}

	/**
	 * 将字符串转换为Integer，转换失败返回defaultValue
	 */
	public static Integer parseInteger(String str, Integer defaultValue) {
		if (str == null)
			return null;
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {

		}
		return defaultValue;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static String md5Sign(Map<String, Object> sArray, boolean isEncode) {
		return MD5(createLinkString(sArray, isEncode)).toLowerCase();
	}

	/**
	 * 判断是否为非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 处理字符串拼接成的数组
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String ckArrStr(String str, String regex, boolean wrapSplit) {
		if (isNotBlank(str)) {
			try {
				String[] arr = str.split(regex);
				StringBuilder sb = new StringBuilder();
				for (String s : arr) {
					if (isNotBlank(s)) {
						sb.append(s).append(regex);
					}
				}
				if (sb.length() > 0) {
					if (wrapSplit) {
						return regex + sb.toString();
					} else {
						return sb.toString().substring(0, sb.length() - 1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> ckArr(String str, String regex, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		if (isNotBlank(str)) {
			try {
				String[] arr = str.split(regex);
				for (String s : arr) {
					if (isNotBlank(s)) {
						if (clazz == String.class) {
							list.add((T) s);
						}
						if (clazz == Integer.class) {
							list.add((T) Integer.valueOf(s));
						}
						if (clazz == Long.class) {
							list.add((T) Long.valueOf(s));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 将文件转成base64 字符串
	 * 
	 * @param path文件路径
	 * @return *
	 * @throws Exception
	 */

	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeBase64String(buffer);

	}

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

}
