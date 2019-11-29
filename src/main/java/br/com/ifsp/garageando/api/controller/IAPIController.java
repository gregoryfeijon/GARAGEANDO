package br.com.ifsp.garageando.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.ifsp.garageando.api.response.Response;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 * 
 * @param <T>
 * @param <D>
 */

interface IAPIController<T, D> {

	public ResponseEntity<Response<D>> findById(D entity);

	public ResponseEntity<List<T>> listAll();

	public ResponseEntity<Response<T>> cadastrar(T entity);

	public ResponseEntity<Response<T>> deletar(T entity);

	public ResponseEntity<Response<T>> deletar(Long id);

	public ResponseEntity<Response<T>> alterar(T entity);
}
