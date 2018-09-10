package com.anhuay.alarm.service;

import com.anhuay.alarm.domain.EmailConfigDO;

import java.util.List;
import java.util.Map;

/**
 * 邮箱配置
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-04 14:42:51
 */
public interface EmailConfigService {
	
	EmailConfigDO get(Long id);
	
	List<EmailConfigDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EmailConfigDO emailConfig);
	
	int update(EmailConfigDO emailConfig);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
    int updateStatus(Long id);
    
    int batchUpdateStatus(Long[] ids);
}
