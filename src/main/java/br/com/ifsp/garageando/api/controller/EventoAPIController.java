package br.com.ifsp.garageando.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.EventoDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Evento;
import br.com.ifsp.garageando.service.EventoService;
import br.com.ifsp.garageando.util.Helpers;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */

@RestController
@RequestMapping("api/evento")
@CrossOrigin(origins = "*")
public class EventoAPIController implements IAPIController<Evento, EventoDTO<Evento>> {

	private static final Logger LOG = LoggerFactory.getLogger(EventoAPIController.class);

	@Autowired
	private EventoService eventoService;

	@PostMapping("/usuario")
	public ResponseEntity<Response<EventoDTO<Evento>>> findByUsuario(@RequestBody EventoDTO<Evento> eventoDTO) {
		LOG.debug("findById({})", eventoDTO.getId());
		List<Evento> listEventos = eventoService.findLocaisByUsuario(eventoDTO.getUsuario());
		if (!Helpers.listEmpty(listEventos)) {
			Response<EventoDTO<Evento>> response = new Response<>();
			eventoDTO.setEntities(listEventos);
			response.setData(eventoDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/endereco")
	public ResponseEntity<Response<EventoDTO<Evento>>> findByEndereco(@RequestBody EventoDTO<Evento> eventoDTO) {
		LOG.debug("findById({})", eventoDTO.getId());
		List<Evento> listEventos = eventoService.findLocaisByEndereco(eventoDTO.getEndereco());
		if (!Helpers.listEmpty(listEventos)) {
			Response<EventoDTO<Evento>> response = new Response<>();
			eventoDTO.setEntities(listEventos);
			response.setData(eventoDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Override
	@PostMapping("/id")
	public ResponseEntity<Response<EventoDTO<Evento>>> findById(@RequestBody EventoDTO<Evento> eventoDTO) {
		LOG.debug("findById({})", eventoDTO.getId());
		Optional<Evento> opEvento = eventoService.findById(eventoDTO.getId());
		if (opEvento.isPresent()) {
			Response<EventoDTO<Evento>> response = new Response<>();
			eventoDTO.setEntity(opEvento.get());
			response.setData(eventoDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Evento>> listAll() {
		LOG.debug("listAll()");
		List<Evento> list = eventoService.findAll();
		if (!Helpers.listEmpty(list)) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PostMapping
	public ResponseEntity<Response<Evento>> cadastrar(@Valid @RequestBody Evento evento) {
		LOG.debug("saving({})", evento);
		Response<Evento> response = new Response<>();
		List<String> erros = eventoService.verificaInformacoesInseridas(evento);
		if (!Helpers.listEmpty(erros)) {
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		Optional<Evento> opEvento = eventoService.save(evento);
		if (opEvento.isPresent()) {
			response.setData(opEvento.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! O cadastro de evento não foi efetuado com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Response<Evento>> deletar(@RequestBody Evento evento) {
		LOG.debug("deleting({})", evento);
		boolean existeEvento = eventoService.existsById(evento.getId());
		if (existeEvento) {
			eventoService.delete(evento);
			existeEvento = eventoService.existsById(evento.getId());
			if (!existeEvento) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Evento> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o evento!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Response<Evento>> deletar(@PathVariable Long id) {
		LOG.debug("deletingById({})", id);
		boolean existeEvento = eventoService.existsById(id);
		if (existeEvento) {
			eventoService.deleteById(id);
			existeEvento = eventoService.existsById(id);
			if (!existeEvento) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Evento> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o evento!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<Response<Evento>> alterar(@Valid @RequestBody Evento evento) {
		LOG.debug("updating({})", evento);
		Response<Evento> response = new Response<>();
		Optional<Evento> opEvento = eventoService.save(evento);
		if (opEvento.isPresent()) {
			response.setData(opEvento.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! A alteração no cadastro de evento não foi efetuada com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

}
