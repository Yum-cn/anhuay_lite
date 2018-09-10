package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主机组
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-29 14:03:49
 */
public class OsGroupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//主机组名称
	private String osGroupName;
	//主机数目
	private String osCount;
	//升级主机编号
	private String osIds;
	//升级主机IP
	private String osIps;
	//备注
	private String remark;
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
	 * 设置：主机组名称
	 */
	public void setOsGroupName(String osGroupName) {
		this.osGroupName = osGroupName;
	}
	/**
	 * 获取：主机组名称
	 */
	public String getOsGroupName() {
		return osGroupName;
	}
	/**
	 * 设置：主机数目
	 */
	public void setOsCount(String osCount) {
		this.osCount = osCount;
	}
	/**
	 * 获取：主机数目
	 */
	public String getOsCount() {
		return osCount;
	}
	/**
	 * 设置：升级主机编号
	 */
	public void setOsIds(String osIds) {
		this.osIds = osIds;
	}
	/**
	 * 获取：升级主机编号
	 */
	public String getOsIds() {
		return osIds;
	}
	/**
	 * 设置：升级主机IP
	 */
	public void setOsIps(String osIps) {
		this.osIps = osIps;
	}
	/**
	 * 获取：升级主机IP
	 */
	public String getOsIps() {
		return osIps;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
