package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
public class Evento implements Serializable {

	private static final long serialVersionUID = 2982597699707445282L;

	private static final String NOME_EVENTO_OBRIGATORIO = "ATENÇÃO! O campo nome do evento é OBRIGATÓRIO!";
	private static final String DATA_OBRIGATORIO = "ATENÇÃO! O campo data do evento é OBRIGATÓRIO!";

	private long id;
	private String nome;
	private Double preco;
	private LocalDate data;
	private Endereco endereco;
	private Usuario usuarioResponsavel;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = NOME_EVENTO_OBRIGATORIO)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotBlank(message = DATA_OBRIGATORIO)
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@JoinColumn(unique = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = Local.ENDERECO_OBRIGATORIO)
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JoinColumn(unique = true)
	@OneToOne(fetch = FetchType.EAGER)
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
}
