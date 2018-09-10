package com.anhuay.os.dao;

import com.anhuay.os.domain.OsGroupDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 主机组
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
@Mapper
public interface OsGroupDao {

	OsGroupDO get(Long id);
	
	List<OsGroupDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsGroupDO osGroup);
	
	int update(OsGroupDO osGroup);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
