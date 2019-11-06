package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.UsuarioPessoaFisica;
import br.com.ifsp.garageando.repository.UsuarioPessoaFisicaRepository;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Service
public class UsuarioPessoaFisicaService implements IService<UsuarioPessoaFisica> {

	@Autowired
	private UsuarioPessoaFisicaRepository usuarioRepository;

	@Override
	public List<UsuarioPessoaFisica> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<UsuarioPessoaFisica> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<UsuarioPessoaFisica> save(UsuarioPessoaFisica usuario) {
		return Optional.of(usuarioRepository.save(usuario));
	}

	@Override
	public void delete(UsuarioPessoaFisica usuario) {
		usuarioRepository.delete(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return usuarioRepository.existsById(id);
	}
}
