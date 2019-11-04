package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
