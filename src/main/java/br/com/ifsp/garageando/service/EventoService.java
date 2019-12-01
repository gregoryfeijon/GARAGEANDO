package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.repository.EventoRepository;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */

@Service
public class EventoService implements IService<Evento> {

	@Autowired
	private EventoRepository eventoRepository;

	@Override
	public Optional<Evento> save(Evento entity) {
		return Optional.ofNullable(eventoRepository.save(entity));
	}

	@Override
	public void delete(Evento entity) {
		eventoRepository.delete(entity);
	}

	@Override
	public void deleteById(Long id) {
		eventoRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return eventoRepository.existsById(id);
	}

	@Override
	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}

	@Override
	public Optional<Evento> findById(Long id) {
		return eventoRepository.findById(id);
	}
}
