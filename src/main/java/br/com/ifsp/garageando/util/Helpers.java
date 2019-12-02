package br.com.ifsp.garageando.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

public class Helpers {

	private static Predicate<List<?>> predicateLista;
	private static Predicate<Object> predicateIsNull;

	public static boolean listEmpty(List<?> entities) {
		if (predicateLista == null) {
			criaPredicateListaVazia();
		}
		return predicateLista.test(entities);
	}

	private static void criaPredicateListaVazia() {
		predicateLista = list -> list == null || list.isEmpty();
	}

	public static boolean isNull(Object entity) {
		if (predicateIsNull == null) {
			criaPredicateIsNull();
		}
		return predicateIsNull.test(entity);
	}

	private static void criaPredicateIsNull() {
		predicateIsNull = p -> p == null;
	}

//	public static <T> boolean listEmpty(List<T> entities) {
//		Predicate<List<T>> predicate = criaPredicateListaVazia();
//		return predicate.test(entities);
//	}
//	
//	private static <T> Predicate<List<T>> criaPredicateListaVazia() {
//		Predicate<List<T>> predicate = list -> list == null || list.isEmpty();
//		return predicate;
//	}
//	
//	public static <T> boolean isNull(T entity) {
//		Predicate<T> predicate = criaPredicateIsNull();
//		return predicate.test(entity);
//	}
//
//	private static <T> Predicate<T> criaPredicateIsNull() {
//		Predicate<T> predicate = p -> p == null;
//		return predicate;
//	}

	public static List<String> processaErros(Map<String, Boolean> map) {
		List<String> erros = new LinkedList<>();
		map.forEach((mensagem, v) -> {
			if (v) {
				erros.add(mensagem);
			}
		});
		return erros;
	}
}
