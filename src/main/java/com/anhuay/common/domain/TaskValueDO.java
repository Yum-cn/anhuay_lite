package com.anhuay.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 同步数据表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-12 12:09:47
 */
public class TaskValueDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自动编号
	private Integer id;
	//表名称
	private String jobName;
	//最后更新时间
	private Date lastUpdateDate;
	//更新数据临界值
	private Long thresholdValue;
	//是否启用
	private String status;

	/**
	 * 设置：自动编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：自动编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：表名称
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * 获取：表名称
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * 设置：最后更新时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：最后更新时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：更新数据临界值
	 */
	public void setThresholdValue(Long thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	/**
	 * 获取：更新数据临界值
	 */
	public Long getThresholdValue() {
		return thresholdValue;
	}
	/**
	 * 设置：是否启用
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：是否启用
	 */
	public String getStatus() {
		return status;
	}
}
