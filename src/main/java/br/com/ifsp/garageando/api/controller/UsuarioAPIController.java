package br.com.ifsp.garageando.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.service.UsuarioService;
import br.com.ifsp.garageando.util.Helpers;
import br.com.ifsp.garageando.util.StringUtil;

/**
 * 27 de nov de 2019
 *
 * @author gregory.feijon
 */

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioAPIController implements IAPIController<Usuario, UsuarioDTO<Usuario>> {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioAPIController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@PostMapping("/email")
	public ResponseEntity<Response<UsuarioDTO<Usuario>>> findByEmail(@RequestBody UsuarioDTO<Usuario> usuarioDTO) {
		LOG.debug("findById({})", usuarioDTO.getId());
		Optional<Usuario> opUsuario = usuarioService.findUsuarioByEmail(usuarioDTO.getEmail());
		if (opUsuario.isPresent()) {
			Response<UsuarioDTO<Usuario>> response = new Response<>();
			usuarioDTO.setEntity(opUsuario.get());
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/loginTeste")
	public ResponseEntity<Response<Usuario>> findByLoginTeste(@RequestBody UsuarioDTO<Usuario> usuarioDTO) {
		LOG.debug("findById({})", usuarioDTO.getId());
		Optional<Usuario> opUsuario = usuarioService.findUsuarioByLogin(usuarioDTO.getLogin());
		if (opUsuario.isPresent()) {
			Response<Usuario> response = new Response<>();
			response.setData(opUsuario.get());
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response<UsuarioDTO<Usuario>>> findByLogin(@RequestBody UsuarioDTO<Usuario> usuarioDTO) {
		LOG.debug("findById({})", usuarioDTO.getId());
		Optional<Usuario> opUsuario = usuarioService.findUsuarioByLogin(usuarioDTO.getLogin());
		if (opUsuario.isPresent()) {
			Response<UsuarioDTO<Usuario>> response = new Response<>();
			usuarioDTO.setEntity(opUsuario.get());
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PostMapping("/id")
	public ResponseEntity<Response<UsuarioDTO<Usuario>>> findById(@RequestBody UsuarioDTO<Usuario> usuarioDTO) {
		LOG.debug("findById({})", usuarioDTO.getId());
		Optional<Usuario> opUsuario = usuarioService.findById(usuarioDTO.getId());
		if (opUsuario.isPresent()) {
			Response<UsuarioDTO<Usuario>> response = new Response<>();
			usuarioDTO.setEntity(opUsuario.get());
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		LOG.debug("listAll()");
		List<Usuario> list = usuarioService.findAll();
		if (!Helpers.listEmpty(list)) {
			return ResponseEntity.ok(list);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PostMapping("/registrar")
	public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody Usuario usuario) {
		LOG.debug("saving({})", usuario);
		Response<Usuario> response = new Response<>();
		if (usuario.getPessoa() == null) {
			response.setErrors(
					Arrays.asList("Erro! Deve ser feito um cadastro de pessoa física junto com o de usuário!"));
			return ResponseEntity.badRequest().body(response);
		}
		usuario.setSenha(bCryptEncoder.encode(usuario.getSenha()));
		Optional<Usuario> opUsuario = usuarioService.save(usuario);
		if (opUsuario.isPresent()) {
			response.setData(opUsuario.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! O cadastro de usuário não foi efetuado com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	@DeleteMapping
	public ResponseEntity<Response<Usuario>> deletar(@RequestBody Usuario usuario) {
		LOG.debug("deleting({})", usuario);
		boolean existeUsuario = usuarioService.existsById(usuario.getId());
		if (existeUsuario) {
			usuarioService.delete(usuario);
			existeUsuario = usuarioService.existsById(usuario.getId());
			if (!existeUsuario) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Usuario> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o usuário!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Response<Usuario>> deletar(@PathVariable Long id) {
		LOG.debug("deletingById({})", id);
		boolean existeUsuario = usuarioService.existsById(id);
		if (existeUsuario) {
			usuarioService.deleteById(id);
			existeUsuario = usuarioService.existsById(id);
			if (!existeUsuario) {
				return ResponseEntity.noContent().build();
			} else {
				Response<Usuario> response = new Response<>();
				response.setErrors(Arrays.asList("Erro ao excluir o usuário!"));
				return ResponseEntity.badRequest().body(response);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	@PutMapping
	public ResponseEntity<Response<Usuario>> alterar(@Valid @RequestBody Usuario usuario) {
		LOG.debug("updating({})", usuario);
		Response<Usuario> response = new Response<>();
		if (StringUtil.isNotNull(usuario.getSenha())) {
			usuario.setSenha(bCryptEncoder.encode(usuario.getSenha()));
		}
		Optional<Usuario> opUsuario = usuarioService.save(usuario);
		if (opUsuario.isPresent()) {
			response.setData(opUsuario.get());
			return ResponseEntity.ok(response);
		} else {
			response.setErrors(Arrays.asList("Erro! A alteração no cadastro de usuário não foi efetuada com sucesso!"));
			return ResponseEntity.badRequest().body(response);
		}
	}
}
