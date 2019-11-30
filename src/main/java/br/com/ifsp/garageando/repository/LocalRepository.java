package br.com.ifsp.garageando.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public interface LocalRepository extends JpaRepository<Local, Long> {

	public Collection<Local> findByUsuarioProprietario(Usuario usuario);

	public Collection<Local> findByEnderecoLocal(Endereco endereco);

}
