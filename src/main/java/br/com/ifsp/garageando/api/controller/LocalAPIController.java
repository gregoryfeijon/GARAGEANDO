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

import br.com.ifsp.garageando.api.dto.LocalDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.service.LocalService;
import br.com.ifsp.garageando.util.Helpers;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

@RestController
@RequestMapping("api/local")
@CrossOrigin(origins = "*")
public class LocalAPIController implements IAPIController<Local, LocalDTO<Local>> {

	private static final Logger LOG = LoggerFactory.getLogger(LocalAPIController.class);

	@Autowired
	private LocalService localService;

	@PostMapping("/usuario")
	public ResponseEntity<Response<LocalDTO<Local>>> findByUsuario(@RequestBody LocalDTO<Local> localDTO) {
		LOG.debug("findById({})", localDTO.getId());
		List<Local> listLocais = localService.findLocaisByUsuario(localDTO.getUsuario());
		if (!Helpers.listEmpty(listLocais)) {
			Response<LocalDTO<Local>> response = new Response<>();
			localDTO.setEntities(listLocais);
			response.setData(localDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/endereco")
	public ResponseEntity<Response<LocalDTO<Local>>> findByEndereco(@RequestBody LocalDTO<Local> localDTO) {
		LOG.debug("findById({})", localDTO.getId());
		List<Local> listLocais = localService.findLocaisByEndereco(localDTO.getEndereco());
		if (!Helpers.listEmpty(listLocais)) {
			Response<LocalDTO<Local>> response = new Response<>();
			localDTO.setEntities(listLocais);
			response.setData(localDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PostMapping("/id")
	public ResponseEntity<Response<LocalDTO<Local>>> findById(@RequestBody LocalDTO<Local> localDTO) {
		LOG.debug("findById({})", localDTO.getId());
		Optional<Local> opLocal = localService.findById(localDTO.getId());
		if (opLocal.isPresent()) {
			Response<LocalDTO<Local>> response = new Response<>();
			localDTO.setEntity(opLocal.get());
			response.setData(localDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Local>> listAll() {
		LOG.debug("listAll()");
		List<Local> list = localService.findAll();
		if (!Helpers.listEmpty(list)) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PostMapping
	public ResponseEntity<Response<Local>> cadastrar(@Valid @RequestBody Local local) {
		LOG.debug("saving({})", local);
		Response<Local> response = new Response<>();
		List<String> erros = localService.verificaInformacoesInseridas(local);
		if (!Helpers.listEmpty(erros)) {
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		Optional<Local> opLocal = localService.save(local);
		if (opLocal.isPresent()) {
			response.setData(opLocal.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! O cadastro de local não foi efetuado com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Response<Local>> deletar(@RequestBody Local local) {
		LOG.debug("deleting({})", local);
		boolean existeLocal = localService.existsById(local.getId());
		if (existeLocal) {
			localService.delete(local);
			existeLocal = localService.existsById(local.getId());
			if (!existeLocal) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Local> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o local!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Response<Local>> deletar(@PathVariable Long id) {
		LOG.debug("deletingById({})", id);
		boolean existeLocal = localService.existsById(id);
		if (existeLocal) {
			localService.deleteById(id);
			existeLocal = localService.existsById(id);
			if (!existeLocal) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Local> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o local!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<Response<Local>> alterar(@Valid @RequestBody Local local) {
		LOG.debug("updating({})", local);
		Response<Local> response = new Response<>();
		Optional<Local> opLocal = localService.save(local);
		if (opLocal.isPresent()) {
			response.setData(opLocal.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! A alteração no cadastro de usuário não foi efetuada com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

}
