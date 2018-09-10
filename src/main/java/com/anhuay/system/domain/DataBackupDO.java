package com.anhuay.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 数据备份表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-07 21:11:30
 */
public class DataBackupDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//备份文件名称
	private String fileName;
	//备注
	private String remark;
	//状态
	private Integer status;
	//修改时间
	private Long updateTime;
	//创建时间
	private Long createTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：备份文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：备份文件名称
	 */
	public String getFileName() {
		return fileName;
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
}
