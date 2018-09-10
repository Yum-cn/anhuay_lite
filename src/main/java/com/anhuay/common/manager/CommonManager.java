package com.anhuay.common.manager;

import java.util.List;

import com.anhuay.common.domain.TreeCode;

/**
 * 管理测试版
 * @author   Yum
 */
public interface CommonManager {

/*	*//**
	 * 保存策略模板
	 * @author   Yum
	 *//*
	BaseResult<Object> saveStrategyTemplet(JSONObject body);
*/

	List<TreeCode> getTreeCode(String treeCodeType);
	
	List<TreeCode> getTreeCodeForTable(String table);
	
	List<TreeCode> getMutiTreeCode(String tables);

}