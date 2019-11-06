package br.com.ifsp.garageando.api.response;

import java.util.List;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 * 
 * @param <T>
 */

public class Response<T> {

	private T data;
	private List<String> errors;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
