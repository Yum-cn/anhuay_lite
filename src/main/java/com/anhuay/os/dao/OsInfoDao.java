package com.anhuay.os.dao;

import com.anhuay.os.domain.OsInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 主机信息表
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
@Mapper
public interface OsInfoDao {

	OsInfoDO get(Long id);
	
	List<OsInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsInfoDO osInfo);
	
	int update(OsInfoDO osInfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int updateStatus(Long id);
	
	int batchUpdateStatus(Long[] ids);
}
