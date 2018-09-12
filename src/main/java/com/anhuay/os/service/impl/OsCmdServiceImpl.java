package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.OsCmdDao;
import com.anhuay.os.domain.OsCmdDO;
import com.anhuay.os.service.OsCmdService;



@Service
public class OsCmdServiceImpl implements OsCmdService {
	@Autowired
	private OsCmdDao osCmdDao;
	
	@Override
	public OsCmdDO get(Integer id){
		return osCmdDao.get(id);
	}
	
	@Override
	public List<OsCmdDO> list(Map<String, Object> map){
		return osCmdDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return osCmdDao.count(map);
	}
	
	@Override
	public int save(OsCmdDO osCmd){
		return osCmdDao.save(osCmd);
	}
	
	@Override
	public int update(OsCmdDO osCmd){
		return osCmdDao.update(osCmd);
	}
	
	@Override
	public int remove(Integer id){
		return osCmdDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return osCmdDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Integer id){
        return osCmdDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Integer[] ids){
        return osCmdDao.batchUpdateStatus(ids);
    }

	@Override
	public OsCmdDO getOsCmd(Map<String, Object> map) {
		return osCmdDao.getOsCmd(map);
	}
}
