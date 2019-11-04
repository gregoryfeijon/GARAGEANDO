package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

}
