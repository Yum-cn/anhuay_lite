package com.anhuay.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户扩展表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-04 17:55:57
 */
public class UserExtendDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户编号
	private Long userId;
	//用户登录规则
	private String loginRules;
	//用户密码规则
	private String passwordRules;
	//其它规则
	private String otherRules;
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
	 * 设置：用户编号
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户编号
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：用户登录规则
	 */
	public void setLoginRules(String loginRules) {
		this.loginRules = loginRules;
	}
	/**
	 * 获取：用户登录规则
	 */
	public String getLoginRules() {
		return loginRules;
	}
	/**
	 * 设置：用户密码规则
	 */
	public void setPasswordRules(String passwordRules) {
		this.passwordRules = passwordRules;
	}
	/**
	 * 获取：用户密码规则
	 */
	public String getPasswordRules() {
		return passwordRules;
	}
	/**
	 * 设置：其它规则
	 */
	public void setOtherRules(String otherRules) {
		this.otherRules = otherRules;
	}
	/**
	 * 获取：其它规则
	 */
	public String getOtherRules() {
		return otherRules;
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
