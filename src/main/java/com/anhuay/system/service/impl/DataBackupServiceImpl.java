package com.anhuay.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.system.dao.DataBackupDao;
import com.anhuay.system.domain.DataBackupDO;
import com.anhuay.system.service.DataBackupService;



@Service
public class DataBackupServiceImpl implements DataBackupService {
	@Autowired
	private DataBackupDao dataBackupDao;
	
	@Override
	public DataBackupDO get(Long id){
		return dataBackupDao.get(id);
	}
	
	@Override
	public List<DataBackupDO> list(Map<String, Object> map){
		return dataBackupDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dataBackupDao.count(map);
	}
	
	@Override
	public int save(DataBackupDO dataBackup){
		return dataBackupDao.save(dataBackup);
	}
	
	@Override
	public int update(DataBackupDO dataBackup){
		return dataBackupDao.update(dataBackup);
	}
	
	@Override
	public int remove(Long id){
		return dataBackupDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return dataBackupDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return dataBackupDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return dataBackupDao.batchUpdateStatus(ids);
    }
}
