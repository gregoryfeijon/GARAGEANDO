package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Avaliacao;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
