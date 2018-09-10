package com.anhuay.system.dao;

import com.anhuay.system.domain.DataBackupDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 数据备份表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-07 21:11:30
 */
@Mapper
public interface DataBackupDao {

	DataBackupDO get(Long id);
	
	List<DataBackupDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DataBackupDO dataBackup);
	
	int update(DataBackupDO dataBackup);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
