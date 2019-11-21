package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

	private static final long serialVersionUID = 2982597699707445282L;

	private static final String NOME_EVENTO_OBRIGATORIO = "ATENÇÃO! O campo nome do evento é OBRIGATÓRIO!";
	private static final String DATA_OBRIGATORIO = "ATENÇÃO! O campo data do evento é OBRIGATÓRIO!";
	private static final String NUMERO_INVALIDO = "ATENÇÃO! O número do local do evento inserido é inválido!";

	private long id;
	private String nome;
	private Double preco;
	private LocalDate data;
	private int numero;
	private String complemento;
	private Endereco enderecoEvento;
	private Usuario usuarioResponsavelEvento;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = NOME_EVENTO_OBRIGATORIO)
	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "PRECO")
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotBlank(message = DATA_OBRIGATORIO)
	@Column(name = "DATA")
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Positive(message = NUMERO_INVALIDO)
	@Column(name = "NUMERO")
	public int getNumero() {
		return numero;
	}

	@Lob
	@Column(name = "COMPLEMENTO")
	public String getComplemento() {
		return complemento;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@JoinColumn(name = "ENDERECO_ID", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_endereco_evento_id"))
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@NotNull(message = Local.ENDERECO_OBRIGATORIO)
	public Endereco getEnderecoEvento() {
		return enderecoEvento;
	}

	public void setEnderecoEvento(Endereco endereco) {
		this.enderecoEvento = endereco;
	}

	@JoinColumn(name = "USUARIO_ID", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_usuario_resp_id"))
	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuarioResponsavelEvento() {
		return usuarioResponsavelEvento;
	}

	public void setUsuarioResponsavelEvento(Usuario usuarioResponsavelEvento) {
		this.usuarioResponsavelEvento = usuarioResponsavelEvento;
	}
}
