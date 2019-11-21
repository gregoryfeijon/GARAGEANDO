package br.com.ifsp.garageando;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.security.enums.Perfil;
import br.com.ifsp.garageando.service.UsuarioService;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@SpringBootApplication
@EnableCaching
public class GarageandoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GarageandoApplication.class, args);
	}

	@Bean
	@Profile({ "dev", "homologacao" })
	CommandLineRunner initDev(UsuarioService usuarioService, BCryptPasswordEncoder bCryptEncoder) {
		return args -> {
			Optional<Usuario> opUsuario = usuarioService.findUsuarioByLogin("admin");
			if (!opUsuario.isPresent()) {
				Usuario usuario = new Usuario();
				usuario.setLogin("admin");
				usuario.setSenha(bCryptEncoder.encode("admin123"));
				usuario.setPerfil(Perfil.ROLE_ADMIN);
				usuarioService.save(usuario);
			}
		};
	}
}
