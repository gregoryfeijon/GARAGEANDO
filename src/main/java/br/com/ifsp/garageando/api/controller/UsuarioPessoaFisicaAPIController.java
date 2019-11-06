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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.UsuarioPessoaFisicaDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.UsuarioPessoaFisica;
import br.com.ifsp.garageando.service.UsuarioPessoaFisicaService;

@RestController
@RequestMapping("api/garageando")
@CrossOrigin(origins = "*")
public class UsuarioPessoaFisicaAPIController implements IAPIController<UsuarioPessoaFisica, UsuarioPessoaFisicaDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioPessoaFisicaAPIController.class);

	@Autowired
	private UsuarioPessoaFisicaService usuarioService;

	@Override
	public ResponseEntity<Response<UsuarioPessoaFisicaDTO>> findById(UsuarioPessoaFisicaDTO usuarioDTO) {
		LOG.debug("findById({})", usuarioDTO.getId());
		Optional<UsuarioPessoaFisica> opUsuario = usuarioService.findById(usuarioDTO.getId());
		if (opUsuario.isPresent()) {
			Response<UsuarioPessoaFisicaDTO> response = new Response<>();
			usuarioDTO.setUsuario(opUsuario.get());
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Override
	public ResponseEntity<Response<UsuarioPessoaFisicaDTO>> listAll() {
		LOG.debug("listAll()");
		List<UsuarioPessoaFisica> list = usuarioService.findAll();
		if (list != null && !list.isEmpty()) {
			UsuarioPessoaFisicaDTO usuarioDTO = new UsuarioPessoaFisicaDTO();
			Response<UsuarioPessoaFisicaDTO> response = new Response<>();
			usuarioDTO.setUsuarios(list);
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Override
	public ResponseEntity<Response<UsuarioPessoaFisica>> cadastrar(@Valid @RequestBody UsuarioPessoaFisica usuario) {
		LOG.debug("saving({})", usuario);
		Response<UsuarioPessoaFisica> response = new Response<>();
		Optional<UsuarioPessoaFisica> opUsuario = usuarioService.save(usuario);
		if (opUsuario.isPresent()) {
			response.setData(opUsuario.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! O cadastro de usuário não foi efetuado com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<Response<UsuarioPessoaFisica>> deletar(UsuarioPessoaFisica usuario) {
		LOG.debug("deleting({})", usuario);
		boolean existeUsuario = usuarioService.existsById(usuario.getId());
		if (existeUsuario) {
			usuarioService.delete(usuario);
			existeUsuario = usuarioService.existsById(usuario.getId());
			if (!existeUsuario) {
				return ResponseEntity.noContent().build();
			} else {
				Response<UsuarioPessoaFisica> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o usuário!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Response<UsuarioPessoaFisica>> deletar(@PathVariable Long id) {
		LOG.debug("deletingById({})", id);
		boolean existeUsuario = usuarioService.existsById(id);
		if (existeUsuario) {
			usuarioService.deleteById(id);
			existeUsuario = usuarioService.existsById(id);
			if (!existeUsuario) {
				return ResponseEntity.noContent().build();
			} else {
				Response<UsuarioPessoaFisica> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o usuário!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Response<UsuarioPessoaFisica>> alterar(UsuarioPessoaFisica usuario) {
		LOG.debug("updating({})", usuario);
		Response<UsuarioPessoaFisica> response = new Response<>();
		Optional<UsuarioPessoaFisica> opUsuario = usuarioService.save(usuario);
		if (opUsuario.isPresent()) {
			response.setData(opUsuario.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! O cadastro de usuário não foi efetuado com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}
}
