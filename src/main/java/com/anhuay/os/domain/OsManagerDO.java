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
public class OsManagerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//主机id（每个id最多只能有一条记录）
	private String osId;
	//主机ip
	private String osIp;
	//应用安全状态
	private String processMonitorStatus;
	//应用安全规则
	private String processMonitorRules;
	//账户安全状态
	private String accountMonitorStatus;
	//账户安全规则
	private String accountMonitorRules;
	//杀毒软件检测状态
	private String sdSoftMonitorStatus;
	//杀毒软件检查规则
	private String sdSoftMonitorRules;
	//网络连接监控状态
	private String netlinkMonitorStatus;
	//网络连接监控规则
	private String netlinkMonitorRules;
	//关闭是否启用密码
	private String closeStatus;
	//关闭密码
	private String closePasswd;
	//卸载是否需要密码
	private String uninstallStatus;
	//卸载密码
	private String uninstallPasswd;
	//策略下发状态（1-待下发，2-下发中，3-已生效，4-配置失败）
	private String taskStatus;
	//创建时间
	private Long createTime;
	//修改时间
	private Long updateTime;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：主机id（每个id最多只能有一条记录）
	 */
	public void setOsId(String osId) {
		this.osId = osId;
	}
	/**
	 * 获取：主机id（每个id最多只能有一条记录）
	 */
	public String getOsId() {
		return osId;
	}
	/**
	 * 设置：主机ip
	 */
	public void setOsIp(String osIp) {
		this.osIp = osIp;
	}
	/**
	 * 获取：主机ip
	 */
	public String getOsIp() {
		return osIp;
	}
	/**
	 * 设置：应用安全状态
	 */
	public void setProcessMonitorStatus(String processMonitorStatus) {
		this.processMonitorStatus = processMonitorStatus;
	}
	/**
	 * 获取：应用安全状态
	 */
	public String getProcessMonitorStatus() {
		return processMonitorStatus;
	}
	/**
	 * 设置：应用安全规则
	 */
	public void setProcessMonitorRules(String processMonitorRules) {
		this.processMonitorRules = processMonitorRules;
	}
	/**
	 * 获取：应用安全规则
	 */
	public String getProcessMonitorRules() {
		return processMonitorRules;
	}
	/**
	 * 设置：账户安全状态
	 */
	public void setAccountMonitorStatus(String accountMonitorStatus) {
		this.accountMonitorStatus = accountMonitorStatus;
	}
	/**
	 * 获取：账户安全状态
	 */
	public String getAccountMonitorStatus() {
		return accountMonitorStatus;
	}
	/**
	 * 设置：账户安全规则
	 */
	public void setAccountMonitorRules(String accountMonitorRules) {
		this.accountMonitorRules = accountMonitorRules;
	}
	/**
	 * 获取：账户安全规则
	 */
	public String getAccountMonitorRules() {
		return accountMonitorRules;
	}
	/**
	 * 设置：杀毒软件检测状态
	 */
	public void setSdSoftMonitorStatus(String sdSoftMonitorStatus) {
		this.sdSoftMonitorStatus = sdSoftMonitorStatus;
	}
	/**
	 * 获取：杀毒软件检测状态
	 */
	public String getSdSoftMonitorStatus() {
		return sdSoftMonitorStatus;
	}
	/**
	 * 设置：杀毒软件检查规则
	 */
	public void setSdSoftMonitorRules(String sdSoftMonitorRules) {
		this.sdSoftMonitorRules = sdSoftMonitorRules;
	}
	/**
	 * 获取：杀毒软件检查规则
	 */
	public String getSdSoftMonitorRules() {
		return sdSoftMonitorRules;
	}
	/**
	 * 设置：网络连接监控状态
	 */
	public void setNetlinkMonitorStatus(String netlinkMonitorStatus) {
		this.netlinkMonitorStatus = netlinkMonitorStatus;
	}
	/**
	 * 获取：网络连接监控状态
	 */
	public String getNetlinkMonitorStatus() {
		return netlinkMonitorStatus;
	}
	/**
	 * 设置：网络连接监控规则
	 */
	public void setNetlinkMonitorRules(String netlinkMonitorRules) {
		this.netlinkMonitorRules = netlinkMonitorRules;
	}
	/**
	 * 获取：网络连接监控规则
	 */
	public String getNetlinkMonitorRules() {
		return netlinkMonitorRules;
	}
	/**
	 * 设置：关闭是否启用密码
	 */
	public void setCloseStatus(String closeStatus) {
		this.closeStatus = closeStatus;
	}
	/**
	 * 获取：关闭是否启用密码
	 */
	public String getCloseStatus() {
		return closeStatus;
	}
	/**
	 * 设置：关闭密码
	 */
	public void setClosePasswd(String closePasswd) {
		this.closePasswd = closePasswd;
	}
	/**
	 * 获取：关闭密码
	 */
	public String getClosePasswd() {
		return closePasswd;
	}
	/**
	 * 设置：卸载是否需要密码
	 */
	public void setUninstallStatus(String uninstallStatus) {
		this.uninstallStatus = uninstallStatus;
	}
	/**
	 * 获取：卸载是否需要密码
	 */
	public String getUninstallStatus() {
		return uninstallStatus;
	}
	/**
	 * 设置：卸载密码
	 */
	public void setUninstallPasswd(String uninstallPasswd) {
		this.uninstallPasswd = uninstallPasswd;
	}
	/**
	 * 获取：卸载密码
	 */
	public String getUninstallPasswd() {
		return uninstallPasswd;
	}
	/**
	 * 设置：策略下发状态（1-待下发，2-下发中，3-已生效，4-配置失败）
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	/**
	 * 获取：策略下发状态（1-待下发，2-下发中，3-已生效，4-配置失败）
	 */
	public String getTaskStatus() {
		return taskStatus;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
}
