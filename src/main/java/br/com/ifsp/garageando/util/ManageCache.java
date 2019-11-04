package br.com.ifsp.garageando.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ManageCache {

	@Autowired
	private CacheManager cacheManager;

	@Scheduled(fixedRateString = "${ clear.all.cache.fixed.rate }", initialDelayString = "${ clear.all.cache.init.delay }")
	public void clearAllCache() {
		cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
	}
}
