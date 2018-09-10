package com.anhuay.alarm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.alarm.dao.EmailConfigDao;
import com.anhuay.alarm.domain.EmailConfigDO;
import com.anhuay.alarm.service.EmailConfigService;



@Service
public class EmailConfigServiceImpl implements EmailConfigService {
	@Autowired
	private EmailConfigDao emailConfigDao;
	
	@Override
	public EmailConfigDO get(Long id){
		return emailConfigDao.get(id);
	}
	
	@Override
	public List<EmailConfigDO> list(Map<String, Object> map){
		return emailConfigDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return emailConfigDao.count(map);
	}
	
	@Override
	public int save(EmailConfigDO emailConfig){
		return emailConfigDao.save(emailConfig);
	}
	
	@Override
	public int update(EmailConfigDO emailConfig){
		return emailConfigDao.update(emailConfig);
	}
	
	@Override
	public int remove(Long id){
		return emailConfigDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return emailConfigDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return emailConfigDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return emailConfigDao.batchUpdateStatus(ids);
    }
}
