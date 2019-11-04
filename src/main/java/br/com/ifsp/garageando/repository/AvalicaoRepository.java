package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Avaliacao;

public interface AvalicaoRepository extends JpaRepository<Avaliacao, Long> {

}
