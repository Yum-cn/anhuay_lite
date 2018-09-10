package com.anhuay.common.license;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author   Yum
 */
//@ConfigurationProperties(value="license.properties")
//@PropertySource(value={"file:conf/license.properties"})
//@Configuration(value="file:D:/new-eclipse/neon-eclipse-workplace/anhuay/target/conf/license.properties")
//@PropertySource(value={"file:license.properties"})
//@Configuration(value="config/license.properties")
public class LicenseProperties {
	
	//@Value("${name}")
	private String name;//名称
	private String macAddress;//物理地址
	private int number;//数量
	private String expirationDate;//过期时间
	private String filePath;//授权文件保存目录
	private String signature;//签名
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return "LicenseProperties [name=" + name + ", macAddress=" + macAddress + ", number=" + number
				+ ", expirationDate=" + expirationDate + ", filePath=" + filePath + ", signature=" + signature + "]";
	}
	
}
