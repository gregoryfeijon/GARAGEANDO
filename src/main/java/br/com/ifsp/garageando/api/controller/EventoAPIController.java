package br.com.ifsp.garageando.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.EventoDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Evento;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */

@RestController
@RequestMapping("api/evento")
@CrossOrigin(origins = "*")
public class EventoAPIController implements IAPIController<Evento, EventoDTO<Evento>> {

	@Override
	public ResponseEntity<Response<EventoDTO<Evento>>> findById(@RequestBody EventoDTO<Evento> entity) {
		return null;
	}

	@Override
	public ResponseEntity<List<Evento>> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Evento>> cadastrar(@Valid @RequestBody Evento entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Evento>> deletar(@RequestBody Evento entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Evento>> deletar(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Evento>> alterar(@Valid @RequestBody Evento entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
