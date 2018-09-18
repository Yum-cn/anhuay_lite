package com.common.util;

import java.util.Set;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenericPropertyConverterUtils {

	public static final char UNDERLINE_CHAR = '_';

	/**
	 * 驼峰转下划线
	 * 
	 * @param camelStr
	 * @return
	 */
	public static String camelToUnderline(String camelStr) {

		if (StringUtils.isEmpty(camelStr)) {

			return StringUtils.EMPTY;
		}

		int len = camelStr.length();
		StringBuilder strb = new StringBuilder(len + len >> 1);
		for (int i = 0; i < len; i++) {

			char c = camelStr.charAt(i);
			if (Character.isUpperCase(c)) {

				strb.append(UNDERLINE_CHAR);
				strb.append(Character.toLowerCase(c));
			} else {

				strb.append(c);
			}
		}
		return strb.toString();
	}

	/**
	 * 下划线转驼峰
	 * 
	 * @param underlineStr
	 * @return
	 */
	public static String underlineToCamel(String underlineStr) {

		if (StringUtils.isEmpty(underlineStr)) {

			return StringUtils.EMPTY;
		}

		int len = underlineStr.length();
		StringBuilder strb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {

			char c = underlineStr.charAt(i);
			if (c == UNDERLINE_CHAR && (++i) < len) {

				c = underlineStr.charAt(i);
				strb.append(Character.toUpperCase(c));
			} else {

				strb.append(c);
			}
		}
		return strb.toString();
	}

	public final static Object camelToUnderlineForJsonArray(JSONArray jsonArray) {

		if (jsonArray != null && jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {

				JSONObject tempJson = jsonArray.getJSONObject(i);
				tempJson = (JSONObject) camelToUnderlineForJsonObject(tempJson);
				jsonArray.element(i, tempJson);
			}
		}
		return jsonArray;
	}

	public final static JSONObject camelToUnderlineForJsonObject(JSONObject json) {

		JSONObject tempJson = (JSONObject) json;
		Set<String> keys = tempJson.keySet();

		JSONObject newObject = new JSONObject();
		String[] array = keys.toArray(new String[keys.size()]);
		for (String key : array) {
			Object value = tempJson.get(key);
			newObject.put(camelToUnderline(key), value);
		}
		return newObject;
	}

	/*
	 * public final static void convert(Object json) { if (json instanceof
	 * JSONArray) { JSONArray arr = (JSONArray) json; for (Object obj : arr) {
	 * convert(obj); } } else if (json instanceof JSONObject) { JSONObject jo =
	 * (JSONObject) json; Set<String> keys = jo.keySet(); String[] array =
	 * keys.toArray(new String[keys.size()]); for (String key : array) { Object
	 * value = jo.get(key); String[] key_strs = key.split("_"); if
	 * (key_strs.length > 1) { StringBuilder sb = new StringBuilder(); for (int
	 * i = 0; i < key_strs.length; i++) { String ks = key_strs[i]; if
	 * (!"".equals(ks)) { if (i == 0) { sb.append(ks); } else { int c =
	 * ks.charAt(0); if (c >= 97 && c <= 122) { int v = c - 32; sb.append((char)
	 * v); if (ks.length() > 1) { sb.append(ks.substring(1)); } } else {
	 * sb.append(ks); } } } } jo.remove(key); jo.put(sb.toString(), value); }
	 * convert(value); } } }
	 */

}
