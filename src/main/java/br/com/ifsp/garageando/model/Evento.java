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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;
	private Double preco;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotBlank
	private LocalDate data;

	@JoinColumn(unique = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = Local.ENDERECO_OBRIGATORIO)
	private Endereco endereco;

	@JoinColumn(unique = true)
	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuarioResponsavel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}
}
