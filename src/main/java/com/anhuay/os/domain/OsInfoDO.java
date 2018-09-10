package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主机信息表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
public class OsInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//主机IP
	private String osIp;
	//主机名
	private String osName;
	//安装码
	private String installCode;
	//终端标识
	private String terminalTag;
	//责任人编号
	private String personLiableId;
	//责任人名称
	private String personLiableName;
	//部门编号
	private Integer deptId;
	//部门名称
	private String deptName;
	//等级(1-公开 2-保密)
	private Integer level;
	//终端类型( 1-单机 2-联网主机)
	private Integer osType;
	//安装状态(1-未安装 2-已安装 3-待卸载)
	private Integer installStatus;
	//在线状态(1-在线 2-离线)
	private Integer onlineStatus;
	//同步状态(1-未同步 2-已同步)
	private Integer syncStatus;
	//最后活动时间
	private Long lastActiveTime;
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
	 * 设置：主机IP
	 */
	public void setOsIp(String osIp) {
		this.osIp = osIp;
	}
	/**
	 * 获取：主机IP
	 */
	public String getOsIp() {
		return osIp;
	}
	/**
	 * 设置：主机名
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}
	/**
	 * 获取：主机名
	 */
	public String getOsName() {
		return osName;
	}
	/**
	 * 设置：安装码
	 */
	public void setInstallCode(String installCode) {
		this.installCode = installCode;
	}
	/**
	 * 获取：安装码
	 */
	public String getInstallCode() {
		return installCode;
	}
	/**
	 * 设置：终端标识
	 */
	public void setTerminalTag(String terminalTag) {
		this.terminalTag = terminalTag;
	}
	/**
	 * 获取：终端标识
	 */
	public String getTerminalTag() {
		return terminalTag;
	}
	/**
	 * 设置：责任人编号
	 */
	public void setPersonLiableId(String personLiableId) {
		this.personLiableId = personLiableId;
	}
	/**
	 * 获取：责任人编号
	 */
	public String getPersonLiableId() {
		return personLiableId;
	}
	/**
	 * 设置：责任人名称
	 */
	public void setPersonLiableName(String personLiableName) {
		this.personLiableName = personLiableName;
	}
	/**
	 * 获取：责任人名称
	 */
	public String getPersonLiableName() {
		return personLiableName;
	}
	/**
	 * 设置：部门编号
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门编号
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：部门名称
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：等级(1-公开 2-保密)
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：等级(1-公开 2-保密)
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：终端类型( 1-单机 2-联网主机)
	 */
	public void setOsType(Integer osType) {
		this.osType = osType;
	}
	/**
	 * 获取：终端类型( 1-单机 2-联网主机)
	 */
	public Integer getOsType() {
		return osType;
	}
	/**
	 * 设置：安装状态(1-未安装 2-已安装 3-待卸载)
	 */
	public void setInstallStatus(Integer installStatus) {
		this.installStatus = installStatus;
	}
	/**
	 * 获取：安装状态(1-未安装 2-已安装 3-待卸载)
	 */
	public Integer getInstallStatus() {
		return installStatus;
	}
	/**
	 * 设置：在线状态(1-在线 2-离线)
	 */
	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	/**
	 * 获取：在线状态(1-在线 2-离线)
	 */
	public Integer getOnlineStatus() {
		return onlineStatus;
	}
	/**
	 * 设置：同步状态(1-未同步 2-已同步)
	 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	/**
	 * 获取：同步状态(1-未同步 2-已同步)
	 */
	public Integer getSyncStatus() {
		return syncStatus;
	}
	/**
	 * 设置：最后活动时间
	 */
	public void setLastActiveTime(Long lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	/**
	 * 获取：最后活动时间
	 */
	public Long getLastActiveTime() {
		return lastActiveTime;
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
