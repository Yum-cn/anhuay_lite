package com.anhuay.os.manager;

import com.anhuay.os.domain.OsManagerVO;
import com.common.util.BaseResult;

/**
 * 管理测试版
 * @author   Yum
 */
public interface OsManagerInterface {

	/**
	 * 保存策略模板
	 * @author   Yum
	 */
	BaseResult<Object> saveOsManager(OsManagerVO bean);

	BaseResult<Object> offlineExport(Object object);

	

}