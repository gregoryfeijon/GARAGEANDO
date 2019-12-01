package br.com.ifsp.garageando.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

	public ResponseEntity<Response<D>> findById(@RequestBody D entity);

	public ResponseEntity<List<T>> listAll();

	public ResponseEntity<Response<T>> cadastrar(@Valid @RequestBody T entity);

	public ResponseEntity<Response<T>> deletar(@RequestBody T entity);

	public ResponseEntity<Response<T>> deletar(@PathVariable Long id);

	public ResponseEntity<Response<T>> alterar(@Valid @RequestBody T entity);
}
