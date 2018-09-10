package com.bootdo.testDemo;

import org.apache.commons.lang.StringUtils;

/**
 * @author Yum
 */
public class TestBoolean {

	public static void main(String[] args) {

		
		boolean osAuditSaveTag = true;
		
		osAuditSaveTag = saveProcessMonitor("",osAuditSaveTag)?true:osAuditSaveTag;
		System.out.println(osAuditSaveTag);
		
	}

	private static boolean saveProcessMonitor(String test, boolean osAuditSaveTag) {
		if (StringUtils.isNotBlank(test)) {
			osAuditSaveTag = true;
		}
		return osAuditSaveTag;
	}

}
