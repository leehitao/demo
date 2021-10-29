package com.xrt.bzj.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.*;

/**
 * 加密签名类
 *
 * @author
 */
public class MD5Util {

	/**
	 * 校验请求参数签名（默认拼接&key=xxx,UTF-8,大写）
	 *
	 * @param jsonStr
	 * @param appKey
	 * @param originSign
	 * @param <T>
	 * @return
	 * @throws
	 */
	public static <T> boolean validateSign(String jsonStr, String appKey, String originSign) {
		if (StringUtils.isEmpty(originSign)) {
			return false;
		}
		String buildSign = buildSign(jsonStr, appKey).toUpperCase();
		return buildSign.equals(originSign);
	}


	/**
	 * 校验请求参数签名（默认拼接&key=xxx,UTF-8,大写）
	 *
	 * @param map
	 * @param appKey
	 * @param originSign
	 * @param <T>
	 * @return
	 * @throws
	 */
	public static <T> boolean validateSign(Map map, String appKey, String originSign) {
		if (StringUtils.isEmpty(originSign)) {
			return false;
		}
		String buildSign = buildSign(map, appKey).toUpperCase();
		return buildSign.equals(originSign);
	}

	/**
	 * 获取签名字符串（默认拼接&key=xxx,UTF-8,大写）
	 *
	 * @param jsonStr
	 * @param appKey
	 * @param <T>
	 * @return
	 */
	public static <T> String buildSign(String jsonStr, String appKey) {

		String signContent = getSignatureContentStr(jsonStr);
		String content = signContent + "&key=" + appKey;
		String sign = md5HexUTF8(content);
		return sign.toUpperCase();
	}

	/**
	 * 获取签名字符串（默认拼接&key=xxx,UTF-8,大写）
	 *
	 * @param map
	 * @param appKey
	 * @param <T>
	 * @return
	 */
	public static <T> String buildSign(Map map, String appKey) {

		String signContent = getSignatureContentStr(map);
		String content = signContent + "&key=" + appKey;
		String sign = md5HexUTF8(content);
		return sign.toUpperCase();
	}

	/**
	 * 获取签名字符串
	 *
	 * @param jsonStr
	 * @param appKey
	 * @param <T>
	 * @return
	 */
	public static <T> String buildSign(String jsonStr, String keyName, String appKey, String charset) {
		String content = getSignatureContentStr(jsonStr);
		content += "&" + keyName + "=" + appKey;
		return md5Hex(content,charset);
	}

	/**
	 * 获取签名字符串
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> String md5Hex(String content, String charset) {
		return DigestUtils.md5Hex(getContentBytes(content, charset));
	}

	/**
	 * 获取签名字符串
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> String md5HexUTF8(String content) {
		return DigestUtils.md5Hex(getContentBytes(content, "UTF-8"));
	}

	/**
	 * 获取实体对应的签名字符串
	 *
	 * @param jsonStr
	 * @param <T>
	 * @return
	 */
	public static <T> String getSignatureContentStr(String jsonStr) {
		String result = "";
		if (jsonStr == null) {
			return result;
		}
		try {
			Map<String, String> map = getSortedField(jsonStr);
			if (map == null) {
				return result;
			}
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append("&");
			}
			String content = sb.toString();
			if ( StringUtils.isBlank(content)) {
				return result;
			}
			// 去除最后的&
			if (content.endsWith("&")) {
				content = StringUtils.substringBeforeLast(content, "&");
			}
			return content;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取Map对应的签名字符串
	 *
	 * @param paramMap
	 * @param <T>
	 * @return
	 */
	public static <T> String getSignatureContentStr(Map<String,Object> paramMap) {
		String jsonStr = JSONObject.toJSONString(paramMap);
		return getSignatureContentStr(jsonStr);
	}



	/**
	 * 获取字典序排序的字符串
	 *
	 * @param
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> TreeMap<String, String> getSortedField(String jsonStr) throws IllegalAccessException {

		TreeMap<String, String> map = new TreeMap<>();
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		Set<String> set = jsonObject.keySet();
		for (String key : set) {
			if (key == null || "sign".equals(key) || "serialVersionUID".equals(key)) {
				continue;
			}
			if (StringUtils.isEmpty(jsonObject.getString(key))) {
				continue;
			}
			map.put(key, jsonObject.getString(key));
		}
		return map;
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}


	public static void main(String[] args) {

		Map<String, String> aa = new HashMap<>();
		aa.put("tid", "1234");
		aa.put("card", "1234***111");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", "jack");
		paramMap.put("sex", "man");
		paramMap.put("age", "19");
		paramMap.put("aa", aa);

		String sign = buildSign(paramMap, "N6P9U6L1U4J5L6Y2C7Y5O4A4W9Z9U9J0");
		paramMap.put("sign", sign);
		System.out.println(sign);
		boolean validate = validateSign(paramMap, "N6P9U6L1U4J5L6Y2C7Y5O4A4W9Z9U9J0", sign);
		System.out.println(validate);

		String paramStr = JSONObject.toJSONString(paramMap, SerializerFeature.WriteNullStringAsEmpty);
		sign = buildSign(paramStr, "N6P9U6L1U4J5L6Y2C7Y5O4A4W9Z9U9J0");
		paramMap.put("sign", sign);
		System.out.println(sign);
		paramStr = JSONObject.toJSONString(paramMap, SerializerFeature.WriteNullStringAsEmpty);
		validate = validateSign(paramStr, "N6P9U6L1U4J5L6Y2C7Y5O4A4W9Z9U9J0", sign);
		System.out.println(validate);
	}


}