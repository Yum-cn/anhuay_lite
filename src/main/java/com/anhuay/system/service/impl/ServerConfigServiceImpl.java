package com.anhuay.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.system.dao.ServerConfigDao;
import com.anhuay.system.domain.ServerConfigDO;
import com.anhuay.system.service.ServerConfigService;



@Service
public class ServerConfigServiceImpl implements ServerConfigService {
	@Autowired
	private ServerConfigDao serverConfigDao;
	
	@Override
	public ServerConfigDO get(Long id){
		return serverConfigDao.get(id);
	}
	
	@Override
	public List<ServerConfigDO> list(Map<String, Object> map){
		return serverConfigDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return serverConfigDao.count(map);
	}
	
	@Override
	public int save(ServerConfigDO serverConfig){
		return serverConfigDao.save(serverConfig);
	}
	
	@Override
	public int update(ServerConfigDO serverConfig){
		return serverConfigDao.update(serverConfig);
	}
	
	@Override
	public int remove(Long id){
		return serverConfigDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return serverConfigDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return serverConfigDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return serverConfigDao.batchUpdateStatus(ids);
    }
}
