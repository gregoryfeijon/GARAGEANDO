package br.com.ifsp.garageando.util;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Component("customKeyGenerator")
public class CacheCustomKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return target.getClass().getSimpleName() + "_" + method.getName() + "_"
				+ StringUtils.arrayToDelimitedString(params, "_");
	}

}
