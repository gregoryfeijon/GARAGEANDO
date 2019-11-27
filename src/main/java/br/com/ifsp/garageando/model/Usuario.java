package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.ifsp.garageando.security.enums.Perfil;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -4210899032728690736L;

	private static final String LOGIN_OBRIGATÓRIO = "ATENÇÃO! O campo login é OBRIGATÓRIO!";
	private static final String SENHA_OBRIGATÓRIO = "ATENÇÃO! O campo senha é OBRIGATÓRIO!";
	private static final String PESSOA_FISICA_OBRIGATÓRIO = "ATENÇÃO! O cadastro de pessoa física é OBRIGATÓRIO!";

	private Long id;
	private String login;
	private String senha;
	private Perfil perfil;
	private PessoaFisica pessoa;
	private Avaliacao avaliacao;
	private List<Local> locaisFavoritos;
	private List<Evento> eventos;
	private List<Local> locaisProprios;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = LOGIN_OBRIGATÓRIO)
	@Length(min = 3)
	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotBlank(message = SENHA_OBRIGATÓRIO)
	@Length(min = 3)
	@Column(name = "SENHA")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "PERFIL")
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@JoinTable(name = "locais", joinColumns = {
			@JoinColumn(name = "USUARIO_ID", table = "usuarios", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "LOCAL_ID", table = "locais", referencedColumnName = "ID") })
	@ManyToMany(fetch = FetchType.LAZY)
	public List<Local> getLocaisFavoritos() {
		return locaisFavoritos;
	}

	public void setLocaisFavoritos(List<Local> locaisFavoritos) {
		this.locaisFavoritos = locaisFavoritos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioAvaliacao")
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioResponsavelEvento")
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	@NotNull(message = PESSOA_FISICA_OBRIGATÓRIO)
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuarioProprietario")
	public List<Local> getLocaisProprios() {
		return locaisProprios;
	}

	public void setLocaisProprios(List<Local> locaisProprios) {
		this.locaisProprios = locaisProprios;
	}
}
