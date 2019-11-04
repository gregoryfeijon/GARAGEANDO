package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

interface IService<T> {

	public List<T> findAll();

	public Optional<T> findById(Long id);

	public Optional<T> save(T entity);

	public void delete(T entity);

	public void deleteById(Long id);
}
