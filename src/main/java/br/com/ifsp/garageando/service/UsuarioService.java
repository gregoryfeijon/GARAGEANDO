package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.repository.UsuarioRepository;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Service
public class UsuarioService implements IService<Usuario> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> save(Usuario usuario) {
		return Optional.of(usuarioRepository.save(usuario));
	}

	@Override
	public void delete(Usuario usuario) {
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

	public Optional<Usuario> findUsuarioByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
}
