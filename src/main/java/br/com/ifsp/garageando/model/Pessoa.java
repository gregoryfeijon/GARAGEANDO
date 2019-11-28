package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifsp.garageando.enums.PessoaTipo;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = 83601349719433879L;

	private static final String NOME_OBRIGATORIO = "Atenção! O campo nome é OBRIGATÓRIO!";
	private static final String PESSOATIPO_OBRIGATORIO = "Atenção! O campo referente ao tipo de pessoa é OBRIGATÓRIO!";
	private static final String TELEFONE_INVALIDO = "Telefone inválido!!!";
	private static final String CELULAR_INVALIDO = "Celular inválido!!!";
	private static final String NOME_INVALIDO = "Nome inválido!!!";
	private static final String EMAIL_INVALIDO = "Email inválido!!!";

	private long id;
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	private LocalDate dataNasc;
	private PessoaTipo pessoaTipo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = NOME_OBRIGATORIO)
//	@NotNull(message = NOME_OBRIGATORIO)
	@Length(min = 3, message = NOME_INVALIDO)
	@Pattern(regexp = "/[a-zA-Z\\u00C0-\\u00FF ]+/i", message = NOME_INVALIDO)
	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$", message = EMAIL_INVALIDO)
	@Column(name = "E_MAIL", length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 10, max = 10, message = TELEFONE_INVALIDO)
	@Column(name = "TELEFONE")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Length(min = 10, max = 11, message = CELULAR_INVALIDO)
	@Column(name = "CELULAR")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

//	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column(name = "DATA_NASC")
	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Enumerated(EnumType.STRING)
	@NotNull(message = PESSOATIPO_OBRIGATORIO)
	@Column(name = "PESSOA_TIPO")
	public PessoaTipo getPessoaTipo() {
		return pessoaTipo;
	}

	public void setPessoaTipo(PessoaTipo pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}
}
