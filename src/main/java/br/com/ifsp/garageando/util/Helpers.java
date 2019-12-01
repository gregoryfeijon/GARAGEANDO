package br.com.ifsp.garageando.util;

import java.util.List;
import java.util.function.Predicate;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

public class Helpers {

	public static <T> boolean listEmpty(List<T> entities) {
		Predicate<List<T>> predicate = criaPredicateListaVazia();
		return predicate.test(entities);
	}
	
	public static <T> boolean isNull(T entity) {
		Predicate<T> predicate = criaPredicateIsNull();
		return predicate.test(entity);
	}

	private static <T> Predicate<List<T>> criaPredicateListaVazia() {
		Predicate<List<T>> predicate = list -> list == null || list.isEmpty();
		return predicate;
	}
	
	private static <T> Predicate<T> criaPredicateIsNull() {
		Predicate<T> predicate = p -> p == null;
		return predicate;
	}
}
