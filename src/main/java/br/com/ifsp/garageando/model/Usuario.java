package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class Usuario extends Pessoa implements Serializable {

	private static final long serialVersionUID = -4210899032728690736L;

	private static final String LOGIN_OBRIGATÓRIO = "ATENÇÃO! O campo login é OBRIGATÓRIO!";
	private static final String SENHA_OBRIGATÓRIO = "ATENÇÃO! O campo senha é OBRIGATÓRIO!";

	@NotBlank(message = LOGIN_OBRIGATÓRIO)
	private String login;

	@NotBlank(message = SENHA_OBRIGATÓRIO)
	private String senha;

	@JoinColumn(unique = true)
	@OneToMany(fetch = FetchType.LAZY)
	private List<Evento> eventos;

	@JoinColumn(unique = true)
	@OneToMany(fetch = FetchType.LAZY)
	private List<Local> locaisProprios;

	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Local> locaisFavoritos;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
