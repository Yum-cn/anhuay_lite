package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-10 17:03:24
 */
public class OsManagerVO implements Serializable {
	private static final long serialVersionUID = 1L;

	// 主机id
	private String osIds;
	// 主机ip
	private String osNames;
	// 应用安全状态
	private String processMonitorStatus;
	//进程黑名单状态
	private String processBlackStatus;
	//进程黑名单规则
	private String processBlackRules;
	//进程白名单状态
	private String processWhiteStatus;
	//进程白名单规则
	private String processWhiteRules;
	// 账户安全状态
	private String accountMonitorStatus;
	//密码加固
	private String passwordRule;
	//杀毒软件检测状态
	private String antiVirusSoftwareStatus;
	
	public String getOsIds() {
		return osIds;
	}
	public void setOsIds(String osIds) {
		this.osIds = osIds;
	}
	public String getOsNames() {
		return osNames;
	}
	public void setOsNames(String osNames) {
		this.osNames = osNames;
	}
	public String getProcessMonitorStatus() {
		return processMonitorStatus;
	}
	public void setProcessMonitorStatus(String processMonitorStatus) {
		this.processMonitorStatus = processMonitorStatus;
	}
	public String getProcessBlackStatus() {
		return processBlackStatus;
	}
	public void setProcessBlackStatus(String processBlackStatus) {
		this.processBlackStatus = processBlackStatus;
	}
	public String getProcessBlackRules() {
		return processBlackRules;
	}
	public void setProcessBlackRules(String processBlackRules) {
		this.processBlackRules = processBlackRules;
	}
	public String getProcessWhiteStatus() {
		return processWhiteStatus;
	}
	public void setProcessWhiteStatus(String processWhiteStatus) {
		this.processWhiteStatus = processWhiteStatus;
	}
	public String getProcessWhiteRules() {
		return processWhiteRules;
	}
	public void setProcessWhiteRules(String processWhiteRules) {
		this.processWhiteRules = processWhiteRules;
	}
	public String getAccountMonitorStatus() {
		return accountMonitorStatus;
	}
	public void setAccountMonitorStatus(String accountMonitorStatus) {
		this.accountMonitorStatus = accountMonitorStatus;
	}
	public String getPasswordRule() {
		return passwordRule;
	}
	public void setPasswordRule(String passwordRule) {
		this.passwordRule = passwordRule;
	}
	public String getAntiVirusSoftwareStatus() {
		return antiVirusSoftwareStatus;
	}
	public void setAntiVirusSoftwareStatus(String antiVirusSoftwareStatus) {
		this.antiVirusSoftwareStatus = antiVirusSoftwareStatus;
	}
	
	
	

}
