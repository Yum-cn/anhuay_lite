package com.anhuay.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 设备表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-10 20:02:56
 */
public class DeviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//设备名称
	private String deviceName;
	//硬件ID
	private String hardwareId;
	//分类编号
	private Long dictId;
	//分类名称
	private String dictName;
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
	 * 设置：设备名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	/**
	 * 获取：设备名称
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * 设置：硬件ID
	 */
	public void setHardwareId(String hardwareId) {
		this.hardwareId = hardwareId;
	}
	/**
	 * 获取：硬件ID
	 */
	public String getHardwareId() {
		return hardwareId;
	}
	/**
	 * 设置：分类编号
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取：分类编号
	 */
	public Long getDictId() {
		return dictId;
	}
	/**
	 * 设置：分类名称
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	/**
	 * 获取：分类名称
	 */
	public String getDictName() {
		return dictName;
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
