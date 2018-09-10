package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.OsGroupOsDao;
import com.anhuay.os.domain.OsGroupOsDO;
import com.anhuay.os.service.OsGroupOsService;



@Service
public class OsGroupOsServiceImpl implements OsGroupOsService {
	@Autowired
	private OsGroupOsDao osGroupOsDao;
	
	@Override
	public OsGroupOsDO get(Long id){
		return osGroupOsDao.get(id);
	}
	
	@Override
	public List<OsGroupOsDO> list(Map<String, Object> map){
		return osGroupOsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osGroupOsDao.count(map);
	}
	
	@Override
	public int save(OsGroupOsDO osGroupOs){
		return osGroupOsDao.save(osGroupOs);
	}
	
	@Override
	public int update(OsGroupOsDO osGroupOs){
		return osGroupOsDao.update(osGroupOs);
	}
	
	@Override
	public int remove(Long id){
		return osGroupOsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osGroupOsDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return osGroupOsDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return osGroupOsDao.batchUpdateStatus(ids);
    }
}
