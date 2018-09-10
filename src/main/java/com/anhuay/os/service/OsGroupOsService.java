package com.anhuay.os.service;

import com.anhuay.os.domain.OsGroupOsDO;

import java.util.List;
import java.util.Map;

/**
 * 主机组主机
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
public interface OsGroupOsService {
	
	OsGroupOsDO get(Long id);
	
	List<OsGroupOsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OsGroupOsDO osGroupOs);
	
	int update(OsGroupOsDO osGroupOs);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
