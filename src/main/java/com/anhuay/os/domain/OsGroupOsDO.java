package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主机组主机
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
public class OsGroupOsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//主机组编号
	private Long osGroupId;
	//主机组名称
	private Long osGroupName;
	//主机编号
	private Long osId;
	//主机名称
	private Long osName;
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
	 * 设置：主机组编号
	 */
	public void setOsGroupId(Long osGroupId) {
		this.osGroupId = osGroupId;
	}
	/**
	 * 获取：主机组编号
	 */
	public Long getOsGroupId() {
		return osGroupId;
	}
	/**
	 * 设置：主机组名称
	 */
	public void setOsGroupName(Long osGroupName) {
		this.osGroupName = osGroupName;
	}
	/**
	 * 获取：主机组名称
	 */
	public Long getOsGroupName() {
		return osGroupName;
	}
	/**
	 * 设置：主机编号
	 */
	public void setOsId(Long osId) {
		this.osId = osId;
	}
	/**
	 * 获取：主机编号
	 */
	public Long getOsId() {
		return osId;
	}
	/**
	 * 设置：主机名称
	 */
	public void setOsName(Long osName) {
		this.osName = osName;
	}
	/**
	 * 获取：主机名称
	 */
	public Long getOsName() {
		return osName;
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
