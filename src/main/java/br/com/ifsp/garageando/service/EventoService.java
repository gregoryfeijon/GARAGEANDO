package br.com.ifsp.garageando.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.repository.EventoRepository;
import br.com.ifsp.garageando.util.Helpers;

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

	public List<Evento> findLocaisByUsuario(Usuario usuario) {
		return eventoRepository.findByUsuarioResponsavelEvento(usuario);
	}

	public List<Evento> findLocaisByEndereco(Endereco endereco) {
		return eventoRepository.findByEnderecoEvento(endereco);
	}

	public List<String> verificaInformacoesInseridas(Evento evento) {
		Map<String, Boolean> map = new HashMap<>();
		map.put("Erro! É necessário especificar um endereço!", Helpers.isNull(evento.getEnderecoEvento()));
		map.put("Erro! É necessário especificar um usuário responsável!",
				Helpers.isNull(evento.getUsuarioResponsavelEvento()));
		map.put("Erro! É necessário especificar um nome!", Helpers.isNull(evento.getNome()));
		map.put("Erro! É necessário especificar uma data!", Helpers.isNull(evento.getData()));
		return Helpers.processaErros(map);
	}
}
