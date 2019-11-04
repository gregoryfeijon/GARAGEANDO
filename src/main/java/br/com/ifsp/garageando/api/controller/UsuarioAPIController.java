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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;
import br.com.ifsp.garageando.api.response.Response;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.service.UsuarioService;

@RestController
@RequestMapping("api/garageando")
@CrossOrigin(origins = "*")
public class UsuarioAPIController implements IAPIController<Usuario, UsuarioDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioAPIController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public ResponseEntity<Response<UsuarioDTO>> findById(UsuarioDTO usuarioDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@GetMapping
	@Override
	public ResponseEntity<Response<UsuarioDTO>> listAll() {
		LOG.debug("listAll()");
		List<Usuario> list = usuarioService.findAll();
		if (list != null && !list.isEmpty()) {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			Response<UsuarioDTO> response = new Response<>();
			usuarioDTO.setUsuarios(list);
			response.setData(usuarioDTO);
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Override
	public ResponseEntity<Response<Usuario>> cadastrar(@Valid @RequestBody Usuario usuario) {
		LOG.debug("saving({})", usuario);
		Response<Usuario> response = new Response<>();
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
	public ResponseEntity<Response<Usuario>> deletar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Usuario>> deletar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Usuario>> alterar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
