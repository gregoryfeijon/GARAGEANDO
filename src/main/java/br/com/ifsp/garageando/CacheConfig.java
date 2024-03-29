package br.com.ifsp.garageando;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
