package com.anhuay.os.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.os.dao.PersonLiableDao;
import com.anhuay.os.domain.PersonLiableDO;
import com.anhuay.os.service.PersonLiableService;



@Service
public class PersonLiableServiceImpl implements PersonLiableService {
	@Autowired
	private PersonLiableDao personLiableDao;
	
	@Override
	public PersonLiableDO get(Long id){
		return personLiableDao.get(id);
	}
	
	@Override
	public List<PersonLiableDO> list(Map<String, Object> map){
		return personLiableDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return personLiableDao.count(map);
	}
	
	@Override
	public int save(PersonLiableDO personLiable){
		return personLiableDao.save(personLiable);
	}
	
	@Override
	public int update(PersonLiableDO personLiable){
		return personLiableDao.update(personLiable);
	}
	
	@Override
	public int remove(Long id){
		return personLiableDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return personLiableDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Long id){
        return personLiableDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Long[] ids){
        return personLiableDao.batchUpdateStatus(ids);
    }
}
