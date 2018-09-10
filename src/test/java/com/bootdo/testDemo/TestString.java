package com.bootdo.testDemo;

import org.apache.commons.lang.StringUtils;

public class TestString {

	public static void main(String[] args) {
		String sourceFileName = "使用须asdddddddddddddd知.tar.gz2342";
		
		System.out.println(StringUtils.indexOf(sourceFileName, "tar.gz"));
		System.out.println("value:"+StringUtils.substringAfterLast(sourceFileName, "tar.gz"));
	}
	/**
	 * @author   Yum
	 */
}
