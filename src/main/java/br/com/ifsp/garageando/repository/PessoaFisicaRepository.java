package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.PessoaFisica;

/**
 * 28 de nov de 2019
 *
 * @author gregory.feijon
 */

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
