package com.yuanyue.component.service;

import org.springframework.cache.annotation.Cacheable;

public class CacheDemo {
	@Cacheable(cacheNames="data")
	public void getData(String name) {
		
	}
}
