package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 *
 * @param <T>
 */

interface IService<T> {

	public List<T> findAll();

	public Optional<T> findById(Long id);

	public Optional<T> save(T entity);

	public void delete(T entity);

	public void deleteById(Long id);

	public boolean existsById(Long id);
}
