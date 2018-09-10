package com.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class SqlUtil implements Serializable {

	/**
	 * @author Yum
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * sql ? 语句拼接
	 * 
	 * @author Yum
	 */
	public static String getParamsPart(Object[] arrays) {
		String paramsPart = "";
		for (int i = 0; i < arrays.length; i++) {
			if (i != 0) {
				paramsPart += ",";
			}
			paramsPart += "?";
		}
		return paramsPart;
	}

	/**
	 * sql in 语句拼接
	 * 
	 * @author Yum
	 */
	public static String getSqlInPart(String fieldString, String regex) {
		String inPart = fieldString.trim();
		String[] inArrays = inPart.split(regex);
		String sqlPart = "";
		if (inArrays != null && inArrays.length > 0) {
			for (int i = 0; i < inArrays.length; i++) {
				if (StringUtils.isNotBlank(sqlPart)) {
					sqlPart += ",";
				}
				sqlPart += "'" + inArrays[i] + "'";
			}
		}
		return sqlPart;
	}

	/**
	 * 
	 * 子句中超过1000会报错，用 where id (1, 2, ..., 1000) or id (1001, ...)
	 * 
	 * @param inValues
	 *            in语句中的集合对象
	 * @param count
	 *            in语句中出现的条件个数
	 * @param field
	 *            in语句对应的数据库查询字段
	 * @return 返回 field in (...) or field in (...) 字符串
	 */
	public static String getSQLIn(List<?> inValues, int count, String field) {
		count = Math.min(count, 1000);
		int len = inValues.size();
		int size = len % count;
		if (size == 0) {
			size = len / count;
		} else {
			size = (len / count) + 1;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int fromIndex = i * count;
			int toIndex = Math.min(fromIndex + count, len);
			// System.out.println(ids.subList(fromIndex, toIndex));
			String productId = StringUtils.defaultIfEmpty(StringUtils.join(inValues.subList(fromIndex, toIndex), "','"),
					"");
			if (i != 0) {
				builder.append(" or ");
			}
			builder.append(field).append(" in ('").append(productId).append("')");
		}

		return StringUtils.defaultIfEmpty(" ("+builder.toString()+") "," ("+field + " in ('')"+") ");
	}

	public static String getGroupSql(List<String[]> list) {

		if (CollectionUtils.isEmpty(list)) {
			return null;
		}

		String sqlPart = "(";

		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			String[] temp = list.get(i);
			int tempIndex = Integer.parseInt(temp[0]);
			if (index == 0) {
				sqlPart += temp[1];
			} else {
				if (tempIndex == index) {
					sqlPart += " and " + temp[1];
				} else {
					sqlPart += " )or( " + temp[1];
				}
			}
			index = tempIndex;
		}
		sqlPart += ")";
		return sqlPart;
	}

	/**
	 * 简单sql注入过滤
	 * @author   Yum
	 */
	public static boolean sqlValidate(String str) {
		str = str.toLowerCase();// 统一转为小写
		String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|"
				+ "table|from|grant|use|group_concat|column_name|"
				+ "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
				+ "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";// 过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");

		System.out.println(getSQLIn(list, 2, "test"));
		System.out.println(getSqlInPart("aa,bbb", ","));
	}

}
