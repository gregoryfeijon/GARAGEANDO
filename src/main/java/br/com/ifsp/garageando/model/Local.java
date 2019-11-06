package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
public class Local implements Serializable {

	private static final long serialVersionUID = -903758268377656191L;

	private static final String PRECO_MEDIO_OBRIGATORIO = "ATENÇÃO! O campo preço médio é OBRIGATÓRIO!";
	public static final String ENDERECO_OBRIGATORIO = "ATENÇÃO! é OBRIGATÓRIO que um local possua um endereço!";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Double largura;
	private Double altura;
	private int numero;
	private boolean isDisponivel;

	@NotBlank(message = PRECO_MEDIO_OBRIGATORIO)
	private Double precoMedioHora;

	@JoinColumn(unique = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = ENDERECO_OBRIGATORIO)
	private Endereco endereco;

	@JoinColumn(unique = true)
	@ManyToOne(fetch = FetchType.LAZY)
	List<Avaliacao> avaliacoes;

	@JoinColumn(unique = false)
	@ManyToMany(fetch = FetchType.EAGER)
	List<FaixaHorarioDisponivel> horariosDisponiveis;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDisponivel() {
		return isDisponivel;
	}

	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	public Double getPrecoMedioHora() {
		return precoMedioHora;
	}

	public void setPrecoMedioHora(Double precoMedioHora) {
		this.precoMedioHora = precoMedioHora;
	}
}
