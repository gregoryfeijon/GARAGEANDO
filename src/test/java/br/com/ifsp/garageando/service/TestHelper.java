package br.com.ifsp.garageando.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.ifsp.garageando.enums.DiaSemana;
import br.com.ifsp.garageando.enums.Estado;
import br.com.ifsp.garageando.enums.PessoaTipo;
import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.FaixaHorarioDisponivel;
import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.model.PessoaJuridica;
import br.com.ifsp.garageando.model.Usuario;
import br.com.ifsp.garageando.model.UsuarioBuilder;
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
	
	public static final String USUARIO_LOGIN = "xoblinhas";
	public static final String USUARIO_SENHA = "xoblas";

	public static final int LOCAL_NUMERO = 628;
	public static final Double LOCAL_PRECO_MEDIO_HORA = 5D;

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
		faixaHorarioRepository.deleteAll();
		avaliacaoRepository.deleteAll();
		localRepository.deleteAll();
		eventoRepository.deleteAll();
		enderecoRepository.deleteAll();
		pessoaJuridicaRepository.deleteAll();
		usuarioRepository.deleteAll();
		pessoaRepository.deleteAll();
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
				{ "Michaelbielson", "michael.bielson@gmail.com", "1140028922", "11998567553", "1996-06-06", "FISICA",
						CPF1 },
				{ "Bielmichaelson", "biel.michaelson@gmail.com", "1189224002", "11975539856", "1996-06-06", "FISICA",
						CPF2 },
				{ "Irineu Matovalino", "irineu.matovalino@gmail.com", "1164332910", "11972596470", "1992-02-20",
						"FISICA", CPF3 } };

		for (String[] dados : data) {
			int idx = 0;
			PessoaFisica pessoa = new PessoaFisica();
			pessoa.setNome(dados[idx++]);
			pessoa.setEmail(dados[idx++]);
			pessoa.setTelefone(dados[idx++]);
			pessoa.setCelular(dados[idx++]);
			pessoa.setDataNasc(LocalDate.parse(dados[idx++]));
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
		// TODO -> CRIAR O CRIA EMPRESA E TESTES!
		return null;
	}

	public List<Usuario> criarUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		criarPessoas(false).stream().forEach(pessoa -> {
			UsuarioBuilder usuarioBuilder = new UsuarioBuilder().withLogin(USUARIO_LOGIN + Math.random())
					.withSenha(bCryptEncoder.encode(USUARIO_SENHA + Math.random())).withPerfil(Perfil.ROLE_USUARIO)
					.withPessoa(pessoa);
			usuarios.add(usuarioBuilder.build());
		});
		usuarioRepository.saveAll(usuarios);
		return usuarios;
	}

	public List<Endereco> criarEnderecos() {
		List<Endereco> enderecos = new LinkedList<>();

		String[][] dados = { { "Salto", "Salto de São José", "Rua dos Curimbatás", "13324275", Estado.SP.toString() },
				{ "Salto", "Centro", "Rua 9 de Julho", "13324000", Estado.SP.toString() },
				{ "Salto", "Centro", "Rua Rio Branco", "13324000", Estado.SP.toString() } };

		for (String data[] : dados) {
			int aux = 0;
			Endereco endereco = new Endereco();
			endereco.setCidade(data[aux++]);
			endereco.setBairro(data[aux++]);
			endereco.setRua(data[aux++]);
			endereco.setCep(Integer.parseInt(data[aux++]));
			endereco.setEstado(Estado.valueOf(data[aux++]));
			enderecos.add(endereco);
		}
		return enderecos;
	}

	public List<FaixaHorarioDisponivel> criarFaixasDeHorario() {
		List<FaixaHorarioDisponivel> faixasDeHorario = new LinkedList<>();

		String[][] dados = { { "08:00", "18:00", DiaSemana.SEGUNDA.toString(), DiaSemana.SEXTA.toString() },
				{ "08:00", "12:00", DiaSemana.SÁBADO.toString(), DiaSemana.SÁBADO.toString() },
				{ "20:00", "00:00", DiaSemana.DOMINGO.toString(), DiaSemana.SEGUNDA.toString() } };

		for (String[] data : dados) {
			int aux = 0;
			FaixaHorarioDisponivel faixaHorario = new FaixaHorarioDisponivel();
			faixaHorario.setHorarioInicio(LocalTime.parse(data[aux++]));
			faixaHorario.setHorarioFim(LocalTime.parse(data[aux++]));
			faixaHorario.setDiaDaSemanaInicio(DiaSemana.valueOf(data[aux++]));
			faixaHorario.setDiaDaSemanaFim(DiaSemana.valueOf(data[aux++]));
			faixasDeHorario.add(faixaHorario);
		}
		return faixasDeHorario;
	}
}
