package com.anhuay.os.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-19 17:42:22
 */
public class NetInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增主键
	private Integer id;
	//关联主机id
	private String osId;
	//关联主机ip
	private String osIp;
	//网卡名称（web显示）
	private String devName;
	//网卡详细说明
	private String devDecript;
	//网卡ip（web显示）
	private String devIp;
	//网卡ipv6地址
	private String devIpv6;
	//掩码
	private String devMask;
	//mac地址（web显示）
	private String devMac;
	//是否启用（1-启用，0-禁用）
	private String devStatus;
	//网卡类型（预留）
	private String devType;
	//预留
	private String attr1;
	//预留
	private String attr2;

	/**
	 * 设置：自增主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：自增主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：关联主机id
	 */
	public void setOsId(String osId) {
		this.osId = osId;
	}
	/**
	 * 获取：关联主机id
	 */
	public String getOsId() {
		return osId;
	}
	/**
	 * 设置：关联主机ip
	 */
	public void setOsIp(String osIp) {
		this.osIp = osIp;
	}
	/**
	 * 获取：关联主机ip
	 */
	public String getOsIp() {
		return osIp;
	}
	/**
	 * 设置：网卡名称（web显示）
	 */
	public void setDevName(String devName) {
		this.devName = devName;
	}
	/**
	 * 获取：网卡名称（web显示）
	 */
	public String getDevName() {
		return devName;
	}
	/**
	 * 设置：网卡详细说明
	 */
	public void setDevDecript(String devDecript) {
		this.devDecript = devDecript;
	}
	/**
	 * 获取：网卡详细说明
	 */
	public String getDevDecript() {
		return devDecript;
	}
	/**
	 * 设置：网卡ip（web显示）
	 */
	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}
	/**
	 * 获取：网卡ip（web显示）
	 */
	public String getDevIp() {
		return devIp;
	}
	/**
	 * 设置：网卡ipv6地址
	 */
	public void setDevIpv6(String devIpv6) {
		this.devIpv6 = devIpv6;
	}
	/**
	 * 获取：网卡ipv6地址
	 */
	public String getDevIpv6() {
		return devIpv6;
	}
	/**
	 * 设置：掩码
	 */
	public void setDevMask(String devMask) {
		this.devMask = devMask;
	}
	/**
	 * 获取：掩码
	 */
	public String getDevMask() {
		return devMask;
	}
	/**
	 * 设置：mac地址（web显示）
	 */
	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}
	/**
	 * 获取：mac地址（web显示）
	 */
	public String getDevMac() {
		return devMac;
	}
	/**
	 * 设置：是否启用（1-启用，0-禁用）
	 */
	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}
	/**
	 * 获取：是否启用（1-启用，0-禁用）
	 */
	public String getDevStatus() {
		return devStatus;
	}
	/**
	 * 设置：网卡类型（预留）
	 */
	public void setDevType(String devType) {
		this.devType = devType;
	}
	/**
	 * 获取：网卡类型（预留）
	 */
	public String getDevType() {
		return devType;
	}
	/**
	 * 设置：预留
	 */
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	/**
	 * 获取：预留
	 */
	public String getAttr1() {
		return attr1;
	}
	/**
	 * 设置：预留
	 */
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	/**
	 * 获取：预留
	 */
	public String getAttr2() {
		return attr2;
	}
}
