package com.anhuay.os.dao;

import com.anhuay.os.domain.NetInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-19 17:42:22
 */
@Mapper
public interface NetInfoDao {

	NetInfoDO get(Integer id);
	
	NetInfoDO getNetInfo(Map<String,Object> map);
	
	List<NetInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(NetInfoDO netInfo);
	
	int update(NetInfoDO netInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int updateStatus(Integer id);
	
	int batchUpdateStatus(Integer[] ids);
}
