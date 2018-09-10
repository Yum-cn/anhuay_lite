package com.anhuay.alarm.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 邮箱配置
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-04 14:42:51
 */
public class EmailConfigDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键编号
	private Long id;
	//邮件服务器地址
	private String emailServerAddress;
	//服务器加密方式
	private String crypt;
	//发件人邮箱地址
	private String senderAddress;
	//发件人邮箱密码
	private String senderPassword;
	//收件人邮箱地址
	private String recipientAddress;
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
	 * 设置：邮件服务器地址
	 */
	public void setEmailServerAddress(String emailServerAddress) {
		this.emailServerAddress = emailServerAddress;
	}
	/**
	 * 获取：邮件服务器地址
	 */
	public String getEmailServerAddress() {
		return emailServerAddress;
	}
	/**
	 * 设置：服务器加密方式
	 */
	public void setCrypt(String crypt) {
		this.crypt = crypt;
	}
	/**
	 * 获取：服务器加密方式
	 */
	public String getCrypt() {
		return crypt;
	}
	/**
	 * 设置：发件人邮箱地址
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	/**
	 * 获取：发件人邮箱地址
	 */
	public String getSenderAddress() {
		return senderAddress;
	}
	/**
	 * 设置：发件人邮箱密码
	 */
	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}
	/**
	 * 获取：发件人邮箱密码
	 */
	public String getSenderPassword() {
		return senderPassword;
	}
	/**
	 * 设置：收件人邮箱地址
	 */
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	/**
	 * 获取：收件人邮箱地址
	 */
	public String getRecipientAddress() {
		return recipientAddress;
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
