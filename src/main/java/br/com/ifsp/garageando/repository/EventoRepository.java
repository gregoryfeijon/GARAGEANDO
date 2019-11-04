package br.com.ifsp.garageando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifsp.garageando.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
