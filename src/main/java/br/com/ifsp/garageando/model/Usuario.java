package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoaFisica")
public class Usuario extends PessoaFisica implements Serializable {

	private static final long serialVersionUID = -4210899032728690736L;

	private static final String LOGIN_OBRIGATÓRIO = "ATENÇÃO! O campo login é OBRIGATÓRIO!";
	private static final String SENHA_OBRIGATÓRIO = "ATENÇÃO! O campo senha é OBRIGATÓRIO!";

	private String login;
	private String senha;
	private List<Local> locaisFavoritos;
	private Avaliacao avaliacao;
	private Evento evento;

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

	@JoinTable(name = "locais", joinColumns = { @JoinColumn(name = "USUARIO_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "LOCAL_ID") })
	@ManyToMany(fetch = FetchType.LAZY)
	public List<Local> getLocaisFavoritos() {
		return locaisFavoritos;
	}

	public void setLocaisFavoritos(List<Local> locaisFavoritos) {
		this.locaisFavoritos = locaisFavoritos;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuarioAvaliacao")
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuarioResponsavelEvento")
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
