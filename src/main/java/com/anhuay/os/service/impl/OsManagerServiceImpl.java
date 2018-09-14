package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.OsManagerDao;
import com.anhuay.os.domain.OsManagerDO;
import com.anhuay.os.service.OsManagerService;



@Service
public class OsManagerServiceImpl implements OsManagerService {
	@Autowired
	private OsManagerDao osManagerDao;
	
	@Override
	public OsManagerDO get(Integer id){
		return osManagerDao.get(id);
	}
	
	@Override
	public List<OsManagerDO> list(Map<String, Object> map){
		return osManagerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osManagerDao.count(map);
	}
	
	@Override
	public int save(OsManagerDO osManager){
		return osManagerDao.save(osManager);
	}
	
	@Override
	public int update(OsManagerDO osManager){
		return osManagerDao.update(osManager);
	}
	
	@Override
	public int remove(Integer id){
		return osManagerDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return osManagerDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Integer id){
        return osManagerDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Integer[] ids){
        return osManagerDao.batchUpdateStatus(ids);
    }

	@Override
	public OsManagerDO getOsManager(Map<String, Object> map) {
		return  osManagerDao.getOsManager(map);
	}
}
