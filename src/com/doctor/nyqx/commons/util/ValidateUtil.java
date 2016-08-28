package com.doctor.nyqx.commons.util;

import java.util.Arrays;

/**
 * 微信校验的接口
 * @author 邓华杰
 *
 */
public class ValidateUtil {
	private static final String TOKEN = "doctordengdenghuajie123";
	public static boolean checkSignature(String signature,String timestamp,String nonce) {
		String[] arr = new String[]{TOKEN,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		
		//生成字符串
		StringBuffer content = new StringBuffer();
		for(int i=0; i<arr.length; i++) {
			content.append(arr[i]);
		}
		//sha1加密
		String temp = SHA1.getSHA1(content.toString());
		return temp.equals(signature);
	}
}
