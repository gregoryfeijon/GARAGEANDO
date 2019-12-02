package br.com.ifsp.garageando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public interface EventoRepository extends JpaRepository<Evento, Long> {

	public List<Evento> findByUsuarioResponsavelEvento(Usuario usuario);

	public List<Evento> findByEnderecoEvento(Endereco endereco);

}
