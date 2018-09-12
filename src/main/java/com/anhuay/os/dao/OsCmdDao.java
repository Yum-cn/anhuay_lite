package com.anhuay.os.dao;

import com.anhuay.os.domain.OsCmdDO;

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
public interface OsCmdDao {

	OsCmdDO get(Integer id);
	
	List<OsCmdDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OsCmdDO osCmd);
	
	int update(OsCmdDO osCmd);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int updateStatus(Integer id);
	
	int batchUpdateStatus(Integer[] ids);

	OsCmdDO getOsCmd(Map<String, Object> map);
}
