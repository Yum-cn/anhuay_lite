package com.anhuay.system.service;

import com.anhuay.system.domain.PropertyDO;

import java.util.List;
import java.util.Map;

/**
 * 属性配置表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-08 15:55:30
 */
public interface PropertyService {
	
	PropertyDO get(Long id);
	
	List<PropertyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PropertyDO property);
	
	int update(PropertyDO property);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
