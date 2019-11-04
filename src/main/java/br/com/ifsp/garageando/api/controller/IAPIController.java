package br.com.ifsp.garageando.api.controller;

import org.springframework.http.ResponseEntity;

import br.com.ifsp.garageando.api.response.Response;

interface IAPIController<T, D> {

	public ResponseEntity<Response<D>> findById(D entity);
	
	public ResponseEntity<Response<D>> listAll();

	public ResponseEntity<Response<T>> cadastrar(T entity);

	public ResponseEntity<Response<T>> deletar(T entity);

	public ResponseEntity<Response<T>> deletar(Long id);

	public ResponseEntity<Response<T>> alterar(T entity);
}
