package com.anhuay.common.domain;

import java.io.Serializable;

public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String name;
	private String referCode;

	public Node() {

	}

	public Node(String id, String parentId, String name, String referCode) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.referCode = referCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", parentId=" + parentId + ", name=" + name + ", referCode=" + referCode + "]";
	}

}
