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
public class OsCmdDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//主机ID
	private String osId;
	//主机ip
	private String osIp;
	//任务状态（1-待下发，2-正在执行，3-已执行成功，4-执行失败）
	private String cmdStatus;
	//任务类型（1-关机，2-重启，3-锁定屏幕）
	private String cmdType;
	//任务执行的具体信息
	private String cmdResult;
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
	 * 设置：主机ID
	 */
	public void setOsId(String osId) {
		this.osId = osId;
	}
	/**
	 * 获取：主机ID
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
	 * 设置：任务状态（1-待下发，2-正在执行，3-已执行成功，4-执行失败）
	 */
	public void setCmdStatus(String cmdStatus) {
		this.cmdStatus = cmdStatus;
	}
	/**
	 * 获取：任务状态（1-待下发，2-正在执行，3-已执行成功，4-执行失败）
	 */
	public String getCmdStatus() {
		return cmdStatus;
	}
	/**
	 * 设置：任务类型（1-关机，2-重启，3-锁定屏幕）
	 */
	public void setCmdType(String cmdType) {
		this.cmdType = cmdType;
	}
	/**
	 * 获取：任务类型（1-关机，2-重启，3-锁定屏幕）
	 */
	public String getCmdType() {
		return cmdType;
	}
	/**
	 * 设置：任务执行的具体信息
	 */
	public void setCmdResult(String cmdResult) {
		this.cmdResult = cmdResult;
	}
	/**
	 * 获取：任务执行的具体信息
	 */
	public String getCmdResult() {
		return cmdResult;
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
