package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.OsInfoDao;
import com.anhuay.os.domain.OsInfoDO;
import com.anhuay.os.service.OsInfoService;



@Service
public class OsInfoServiceImpl implements OsInfoService {
	@Autowired
	private OsInfoDao osInfoDao;
	
	@Override
	public OsInfoDO get(Long id){
		return osInfoDao.get(id);
	}
	
	@Override
	public List<OsInfoDO> list(Map<String, Object> map){
		return osInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osInfoDao.count(map);
	}
	
	@Override
	public int save(OsInfoDO osInfo){
		return osInfoDao.save(osInfo);
	}
	
	@Override
	public int update(OsInfoDO osInfo){
		return osInfoDao.update(osInfo);
	}
	
	@Override
	public int remove(Long id){
		return osInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return osInfoDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return osInfoDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return osInfoDao.batchUpdateStatus(ids);
    }
}
