package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.OsGroupDao;
import com.anhuay.os.domain.OsGroupDO;
import com.anhuay.os.service.OsGroupService;



@Service
public class OsGroupServiceImpl implements OsGroupService {
	@Autowired
	private OsGroupDao osGroupDao;
	
	@Override
	public OsGroupDO get(Long id){
		return osGroupDao.get(id);
	}
	
	@Override
	public List<OsGroupDO> list(Map<String, Object> map){
		return osGroupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osGroupDao.count(map);
	}
	
	@Override
	public int save(OsGroupDO osGroup){
		return osGroupDao.save(osGroup);
	}
	
	@Override
	public int update(OsGroupDO osGroup){
		return osGroupDao.update(osGroup);
	}
	
	@Override
	public int remove(Long id){
		return osGroupDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osGroupDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return osGroupDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return osGroupDao.batchUpdateStatus(ids);
    }
}
