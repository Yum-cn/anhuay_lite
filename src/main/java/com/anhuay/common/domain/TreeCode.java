package com.anhuay.common.domain;

import java.io.Serializable;

public class TreeCode implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String treeCodeId;// vancloud系统编号
	private String treeCodeName;// 编码名称
	private String parentId; // 父节点
	private String referCode; // 其他系统编码

	public String getTreeCodeId()
	{
		return treeCodeId;
	}

	public void setTreeCodeId(String treeCodeId)
	{
		this.treeCodeId = treeCodeId;
	}

	public String getTreeCodeName()
	{
		return treeCodeName;
	}

	public void setTreeCodeName(String treeCodeName)
	{
		this.treeCodeName = treeCodeName;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getReferCode()
	{
		return referCode;
	}

	public void setReferCode(String referCode)
	{
		this.referCode = referCode;
	}

	@Override
	public String toString()
	{
		return "TreeCode [treeCodeId=" + treeCodeId + ", treeCodeName=" + treeCodeName + ", parentId=" + parentId + ", referCode=" + referCode + "]";
	}

}
