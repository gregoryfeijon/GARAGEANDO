package br.com.ifsp.garageando.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.repository.UsuarioRepository;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Service(value = "userService")
public class UsuarioService implements IService<Usuario>, UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

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

	public Optional<Usuario> findUsuarioByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> opUsuario = findUsuarioByLogin(login);
		if (opUsuario.isPresent()) {
			Usuario usuario = opUsuario.get();
			List<SimpleGrantedAuthority> perfil = getAuthority(usuario);
			return new User(usuario.getLogin(), usuario.getSenha(), perfil);
		} else {
			throw new UsernameNotFoundException("Nao encontrou o usuario para o login: " + login);
		}
	}

	private List<SimpleGrantedAuthority> getAuthority(Usuario usuario) {
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(usuario.getPerfil().name()));
		return list;
	}
}
