package com.anhuay.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anhuay.common.domain.LogDO;
import com.anhuay.common.domain.PageDO;
import com.anhuay.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
