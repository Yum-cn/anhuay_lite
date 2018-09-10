package com.anhuay.system.service;

import com.anhuay.common.domain.DictDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.system.domain.DeviceDO;

import java.util.List;
import java.util.Map;

/**
 * 设备表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-10 20:02:56
 */
public interface DeviceService {
	
	DeviceDO get(Long id);
	
	List<DeviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceDO device);
	
	int update(DeviceDO device);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);

	Tree<DictDO> getTree(Map<String, Object> map);
}
