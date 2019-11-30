package br.com.ifsp.garageando.util;

import java.util.List;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

public class Helpers {

	public static <T> boolean listEmpty(List<T> entities) {
		if (entities == null || entities.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
