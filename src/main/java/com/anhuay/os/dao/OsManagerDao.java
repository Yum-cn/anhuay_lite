package com.anhuay.os.dao;

import com.anhuay.os.domain.OsManagerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-10 17:03:24
 */
@Mapper
public interface OsManagerDao {

	OsManagerDO get(Integer id);
	
	List<OsManagerDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsManagerDO osManager);
	
	int update(OsManagerDO osManager);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int updateStatus(Integer id);
	
	int batchUpdateStatus(Integer[] ids);

	OsManagerDO getOsManager(Map<String, Object> map);
}
