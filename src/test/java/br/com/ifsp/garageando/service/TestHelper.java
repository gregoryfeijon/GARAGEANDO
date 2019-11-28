package br.com.ifsp.garageando.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.ifsp.garageando.enums.PessoaTipo;
import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.model.PessoaJuridica;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.repository.AvaliacaoRepository;
import br.com.ifsp.garageando.repository.EnderecoRepository;
import br.com.ifsp.garageando.repository.EventoRepository;
import br.com.ifsp.garageando.repository.FaixaHorarioDisponivelRepository;
import br.com.ifsp.garageando.repository.LocalRepository;
import br.com.ifsp.garageando.repository.PessoaFisicaRepository;
import br.com.ifsp.garageando.repository.PessoaJuridicaRepository;
import br.com.ifsp.garageando.repository.UsuarioRepository;
import br.com.ifsp.garageando.security.enums.Perfil;

/**
 * 27 de nov de 2019
 *
 * @author gregory.feijon
 */

@Component
public class TestHelper {

	private static final String CPF1 = "34540622099";
	private static final String CPF2 = "24818682004";
	private static final String CPF3 = "19317293018";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PessoaFisicaRepository pessoaRepository;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private FaixaHorarioDisponivelRepository faixaHorarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void limpaBanco() {
		pessoaRepository.deleteAll();
		pessoaJuridicaRepository.deleteAll();
		usuarioRepository.deleteAll();
		localRepository.deleteAll();
		faixaHorarioRepository.deleteAll();
		enderecoRepository.deleteAll();
		eventoRepository.deleteAll();
		avaliacaoRepository.deleteAll();
		entityManager.createNativeQuery("ALTER TABLE usuarios AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE avaliacoes AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE enderecos AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE eventos AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE horarios_locais AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE locais AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE pessoa_fisica AUTO_INCREMENT = 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE pessoa_juridica AUTO_INCREMENT = 1").executeUpdate();
	}

	public void criaUsuarioAdmin() {
		Optional<Usuario> opUsuario = usuarioRepository.findByLogin("admin");
		if (!opUsuario.isPresent()) {
			Usuario usuario = new Usuario();
			usuario.setLogin("admin");
			usuario.setSenha(bCryptEncoder.encode("admin123"));
			usuario.setPerfil(Perfil.ROLE_ADMIN);
			usuarioRepository.save(usuario);
		}
	}

	public List<PessoaFisica> criarPessoas(boolean criarEmpresas) {
		List<PessoaFisica> pessoas = new LinkedList<>();

		String[][] data = {
				{ "Michaelbielson", "michael.bielson@gmail.com", "1140028922", "11998567553", "06/06/1996", "FISICA",
						CPF1 },
				{ "Bielmichaelson", "biel.michaelson@gmail.com", "1189224002", "11975539856", "06/06/1996", "FISICA",
						CPF2 },
				{ "Irineu Matovalino", "irineu.matovalino@gmail.com", "1164332910", "11972596470", "20/02/1992",
						"FISICA", CPF3 } };

		for (String[] dados : data) {
			int idx = 0;
			PessoaFisica pessoa = new PessoaFisica();
			pessoa.setNome(dados[idx++]);
			pessoa.setEmail(dados[idx++]);
			pessoa.setTelefone(dados[idx++]);
			pessoa.setCelular(dados[idx++]);
			pessoa.setDataNasc(LocalDate.parse(dados[idx++], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			pessoa.setPessoaTipo(PessoaTipo.valueOf(dados[idx++]));
			pessoa.setCpf(dados[idx++]);
			pessoas.add(pessoa);
		}
		if (criarEmpresas) {
			pessoas.stream().forEach(pessoa -> pessoa.setEmpresas(criaEmpresa(pessoa)));
		}
		return pessoas;
	}

	private List<PessoaJuridica> criaEmpresa(PessoaFisica pessoa) {
		return null;
	}

}
