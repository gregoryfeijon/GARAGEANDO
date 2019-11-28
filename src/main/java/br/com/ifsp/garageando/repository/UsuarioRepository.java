package br.com.ifsp.garageando.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ifsp.garageando.model.Usuario;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByLogin(String login);

	@Query("SELECT u, p FROM Usuario u JOIN FETCH u.pessoa p WHERE p.email = :email")
	public Optional<Usuario> findByEmail(String email);
}
