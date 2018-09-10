package com.anhuay.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.anhuay.common.dao.TaskValueDao;
import com.anhuay.common.domain.TaskValueDO;
import com.anhuay.common.service.TaskValueService;



@Service
public class TaskValueServiceImpl implements TaskValueService {
	@Autowired
	private TaskValueDao taskValueDao;
	
	@Override
	public TaskValueDO get(Integer id){
		return taskValueDao.get(id);
	}
	
	@Override
	public List<TaskValueDO> list(Map<String, Object> map){
		return taskValueDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskValueDao.count(map);
	}
	
	@Override
	public int save(TaskValueDO taskValue){
		return taskValueDao.save(taskValue);
	}
	
	@Override
	public int update(TaskValueDO taskValue){
		return taskValueDao.update(taskValue);
	}
	
	@Override
	public int remove(Integer id){
		return taskValueDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return taskValueDao.batchRemove(ids);
	}
	
	   
    @Override
    public int updateStatus(Integer id){
        return taskValueDao.updateStatus(id);
    }
    
    @Override
    public int batchUpdateStatus(Integer[] ids){
        return taskValueDao.batchUpdateStatus(ids);
    }
}
