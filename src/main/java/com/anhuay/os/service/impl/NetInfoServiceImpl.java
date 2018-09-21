package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.NetInfoDao;
import com.anhuay.os.domain.NetInfoDO;
import com.anhuay.os.service.NetInfoService;



@Service
public class NetInfoServiceImpl implements NetInfoService {
	@Autowired
	private NetInfoDao netInfoDao;
	
	@Override
	public NetInfoDO get(Integer id){
		return netInfoDao.get(id);
	}
	
	@Override
    public NetInfoDO getNetInfo(Map<String, Object> map){
        return netInfoDao.getNetInfo(map);
    }
	
	@Override
	public List<NetInfoDO> list(Map<String, Object> map){
		return netInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return netInfoDao.count(map);
	}
	
	@Override
	public int save(NetInfoDO netInfo){
		return netInfoDao.save(netInfo);
	}
	
	@Override
	public int update(NetInfoDO netInfo){
		return netInfoDao.update(netInfo);
	}
	
	@Override
	public int remove(Integer id){
		return netInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return netInfoDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Integer id){
        return netInfoDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Integer[] ids){
        return netInfoDao.batchUpdateStatus(ids);
    }
}
