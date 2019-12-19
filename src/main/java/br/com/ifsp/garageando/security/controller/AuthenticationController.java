package br.com.ifsp.garageando.security.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifsp.garageando.api.dto.UsuarioDTO;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.security.config.JwtTokenUtil;
import br.com.ifsp.garageando.service.UsuarioService;

/**
 * 6 de nov de 2019
 *
 * @author gregory.feijon
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/autenticacao")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/obter-token")
	public ResponseEntity<String> register(@RequestBody UsuarioDTO<Usuario> usuarioDTO) throws AuthenticationException {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getLogin(), usuarioDTO.getSenha()));
		final Optional<Usuario> usuario = usuarioService.findUsuarioByLogin(usuarioDTO.getLogin());
		if (usuario.isPresent()) {
			final String token = jwtTokenUtil.generateToken(usuario.get());
			return ResponseEntity.ok(token);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
