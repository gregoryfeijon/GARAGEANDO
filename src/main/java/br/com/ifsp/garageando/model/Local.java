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
import javax.persistence.OneToOne;
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

	private long id;
	private Double largura;
	private Double altura;
	private int numero;
	private boolean isDisponivel;
	private Double precoMedioHora;
	private Endereco endereco;
	private List<Avaliacao> avaliacoes;
	private List<FaixaHorarioDisponivel> horariosDisponiveis;
	private Usuario usuarioProprietario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@NotBlank(message = PRECO_MEDIO_OBRIGATORIO)
	public Double getPrecoMedioHora() {
		return precoMedioHora;
	}

	public void setPrecoMedioHora(Double precoMedioHora) {
		this.precoMedioHora = precoMedioHora;
	}

	@JoinColumn(unique = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = ENDERECO_OBRIGATORIO)
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@JoinColumn(unique = true)
	@ManyToOne(fetch = FetchType.LAZY)
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@JoinColumn(unique = false)
	@ManyToMany(fetch = FetchType.EAGER)
	public List<FaixaHorarioDisponivel> getHorariosDisponiveis() {
		return horariosDisponiveis;
	}

	public void setHorariosDisponiveis(List<FaixaHorarioDisponivel> horariosDisponiveis) {
		this.horariosDisponiveis = horariosDisponiveis;
	}

	@JoinColumn(unique = true)
	@OneToOne(fetch = FetchType.EAGER)
	public Usuario getUsuarioProprietario() {
		return usuarioProprietario;
	}

	public void setUsuarioProprietario(Usuario usuarioProprietario) {
		this.usuarioProprietario = usuarioProprietario;
	}
}
