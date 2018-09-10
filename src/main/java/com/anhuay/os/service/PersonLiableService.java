package com.anhuay.os.service;

import com.anhuay.os.domain.PersonLiableDO;

import java.util.List;
import java.util.Map;

/**
 * 责任人表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-23 23:35:07
 */
public interface PersonLiableService {
	
	PersonLiableDO get(Long id);
	
	List<PersonLiableDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonLiableDO personLiable);
	
	int update(PersonLiableDO personLiable);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
