package com.common.manager.impl;

import com.common.id.IdWorker;
import com.common.manager.BaseManager;

public abstract class BaseManagerImpl implements BaseManager {

	// private RedisTemplate<String, Object> redisTemplate;

	protected IdWorker idWorker = new IdWorker(1);

}
