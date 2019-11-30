package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.repository.LocalRepository;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

@Service
public class LocalService implements IService<Local> {

	@Autowired
	private LocalRepository localRepository;

	@Override
	public Optional<Local> save(Local local) {
		return Optional.ofNullable(localRepository.save(local));
	}

	@Override
	public void delete(Local local) {
		localRepository.delete(local);
	}

	@Override
	public void deleteById(Long id) {
		localRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return localRepository.existsById(id);
	}

	@Override
	public List<Local> findAll() {
		return localRepository.findAll();
	}

	@Override
	public Optional<Local> findById(Long id) {
		return localRepository.findById(id);
	}

	public List<Local> findLocaisByUsuario(Usuario usuario) {
		return localRepository.findByUsuarioProprietario(usuario).stream().collect(Collectors.toList());
	}

	public List<Local> findLocaisByEndereco(Endereco endereco) {
		return localRepository.findByEnderecoLocal(endereco).stream().collect(Collectors.toList());
	}
}
