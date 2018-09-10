package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 责任人表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-23 23:35:07
 */
public class PersonLiableDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//部门编号
	private Long deptId;
	//部门名称
	private String deptName;
	//责任人名称
	private String personLiableName;
	//电话
	private String phone;
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
	 * 设置：部门编号
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门编号
	 */
	public Long getDeptId() {
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
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
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
