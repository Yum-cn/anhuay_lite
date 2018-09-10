package com.anhuay.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 服务配置表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-07 15:22:38
 */
public class ServerConfigDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//TOMCAT服务器IP
	private String webServerIp;
	//登录超时时间(分钟)
	private Integer sessionTimeout;
	//审计日志保存周期
	private String saveCycle;
	//审计日志将满告警剩余空间设置(M)
	private Integer spareSpace;
	//审计日志存储空间上限(M)
	private Integer maxStorageSpace;
	//审计日志停止上报标识(缓存到终端本地)
	private Integer stopReportTag;
	//告警方式(一般行为)
	private String alarmModeNormal;
	//告警方式(异常行为)
	private String alarmModeException;
	//告警方式(违规行为)
	private String alarmModeError;
	//日志及数据备份解压密码
	private String decoPassword;
	//与时钟服务器时间同步标识
	private Integer serverClockSyncTag;
	//时钟服务器地址
	private String clockServerAddress;
	//管理端同步频率(分钟)
	private Integer serverSyncFreq;
	//弹出气泡标识
	private Integer bubbleTag;
	//与时钟服务器时间同步标识(客户端)
	private Integer clientClockSyncTag;
	//客户端同步频率(分钟)
	private Integer clientSyncFreq;
	//状态
	private Integer status;
	//创建时间
	private Long createTime;
	//修改时间
	private Long updateTime;

	/**
	 * 设置：主键编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：TOMCAT服务器IP
	 */
	public void setWebServerIp(String webServerIp) {
		this.webServerIp = webServerIp;
	}
	/**
	 * 获取：TOMCAT服务器IP
	 */
	public String getWebServerIp() {
		return webServerIp;
	}
	/**
	 * 设置：登录超时时间(分钟)
	 */
	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	/**
	 * 获取：登录超时时间(分钟)
	 */
	public Integer getSessionTimeout() {
		return sessionTimeout;
	}
	/**
	 * 设置：审计日志保存周期
	 */
	public void setSaveCycle(String saveCycle) {
		this.saveCycle = saveCycle;
	}
	/**
	 * 获取：审计日志保存周期
	 */
	public String getSaveCycle() {
		return saveCycle;
	}
	/**
	 * 设置：审计日志将满告警剩余空间设置(M)
	 */
	public void setSpareSpace(Integer spareSpace) {
		this.spareSpace = spareSpace;
	}
	/**
	 * 获取：审计日志将满告警剩余空间设置(M)
	 */
	public Integer getSpareSpace() {
		return spareSpace;
	}
	/**
	 * 设置：审计日志存储空间上限(M)
	 */
	public void setMaxStorageSpace(Integer maxStorageSpace) {
		this.maxStorageSpace = maxStorageSpace;
	}
	/**
	 * 获取：审计日志存储空间上限(M)
	 */
	public Integer getMaxStorageSpace() {
		return maxStorageSpace;
	}
	/**
	 * 设置：审计日志停止上报标识(缓存到终端本地)
	 */
	public void setStopReportTag(Integer stopReportTag) {
		this.stopReportTag = stopReportTag;
	}
	/**
	 * 获取：审计日志停止上报标识(缓存到终端本地)
	 */
	public Integer getStopReportTag() {
		return stopReportTag;
	}
	/**
	 * 设置：告警方式(一般行为)
	 */
	public void setAlarmModeNormal(String alarmModeNormal) {
		this.alarmModeNormal = alarmModeNormal;
	}
	/**
	 * 获取：告警方式(一般行为)
	 */
	public String getAlarmModeNormal() {
		return alarmModeNormal;
	}
	/**
	 * 设置：告警方式(异常行为)
	 */
	public void setAlarmModeException(String alarmModeException) {
		this.alarmModeException = alarmModeException;
	}
	/**
	 * 获取：告警方式(异常行为)
	 */
	public String getAlarmModeException() {
		return alarmModeException;
	}
	/**
	 * 设置：告警方式(违规行为)
	 */
	public void setAlarmModeError(String alarmModeError) {
		this.alarmModeError = alarmModeError;
	}
	/**
	 * 获取：告警方式(违规行为)
	 */
	public String getAlarmModeError() {
		return alarmModeError;
	}
	/**
	 * 设置：日志及数据备份解压密码
	 */
	public void setDecoPassword(String decoPassword) {
		this.decoPassword = decoPassword;
	}
	/**
	 * 获取：日志及数据备份解压密码
	 */
	public String getDecoPassword() {
		return decoPassword;
	}
	/**
	 * 设置：与时钟服务器时间同步标识
	 */
	public void setServerClockSyncTag(Integer serverClockSyncTag) {
		this.serverClockSyncTag = serverClockSyncTag;
	}
	/**
	 * 获取：与时钟服务器时间同步标识
	 */
	public Integer getServerClockSyncTag() {
		return serverClockSyncTag;
	}
	/**
	 * 设置：时钟服务器地址
	 */
	public void setClockServerAddress(String clockServerAddress) {
		this.clockServerAddress = clockServerAddress;
	}
	/**
	 * 获取：时钟服务器地址
	 */
	public String getClockServerAddress() {
		return clockServerAddress;
	}
	/**
	 * 设置：管理端同步频率(分钟)
	 */
	public void setServerSyncFreq(Integer serverSyncFreq) {
		this.serverSyncFreq = serverSyncFreq;
	}
	/**
	 * 获取：管理端同步频率(分钟)
	 */
	public Integer getServerSyncFreq() {
		return serverSyncFreq;
	}
	/**
	 * 设置：弹出气泡标识
	 */
	public void setBubbleTag(Integer bubbleTag) {
		this.bubbleTag = bubbleTag;
	}
	/**
	 * 获取：弹出气泡标识
	 */
	public Integer getBubbleTag() {
		return bubbleTag;
	}
	/**
	 * 设置：与时钟服务器时间同步标识(客户端)
	 */
	public void setClientClockSyncTag(Integer clientClockSyncTag) {
		this.clientClockSyncTag = clientClockSyncTag;
	}
	/**
	 * 获取：与时钟服务器时间同步标识(客户端)
	 */
	public Integer getClientClockSyncTag() {
		return clientClockSyncTag;
	}
	/**
	 * 设置：客户端同步频率(分钟)
	 */
	public void setClientSyncFreq(Integer clientSyncFreq) {
		this.clientSyncFreq = clientSyncFreq;
	}
	/**
	 * 获取：客户端同步频率(分钟)
	 */
	public Integer getClientSyncFreq() {
		return clientSyncFreq;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
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
