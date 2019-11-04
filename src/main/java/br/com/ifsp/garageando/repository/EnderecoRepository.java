package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
