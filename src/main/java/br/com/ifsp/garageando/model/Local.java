package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "locais")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Local implements Serializable {

	private static final long serialVersionUID = -903758268377656191L;

	private static final String PRECO_MEDIO_OBRIGATORIO = "ATENÇÃO! O campo preço médio é OBRIGATÓRIO!";
	private static final String USUARIO_OBRIGATORIO = "ATENÇÃO! O campo referente a usuário é OBRIGATÓRIO!";
	public static final String ENDERECO_OBRIGATORIO = "ATENÇÃO! é OBRIGATÓRIO que um local possua um endereço!";

	private Long id;
	private Double largura;
	private Double altura;
	private boolean isDisponivel = true;
	private Double precoMedioHora;
	private int numero;
	private Endereco enderecoLocal;
	private List<Avaliacao> avaliacoes;
	private List<FaixaHorarioDisponivel> faixasHorariosDisponiveis;
	private Usuario usuarioProprietario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LARGURA")
	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	@Column(name = "ALTURA")
	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	@Column(name = "NUMERO")
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Column(name = "DISPONIVEL", nullable = false, columnDefinition = "TINYINT(1) NOT NULL DEFAULT TRUE")
	public boolean isDisponivel() {
		return isDisponivel;
	}

	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	@NotNull(message = PRECO_MEDIO_OBRIGATORIO)
	@Column(name = "PRECO_MEDIO_HORA", nullable = false)
	public Double getPrecoMedioHora() {
		return precoMedioHora;
	}

	public void setPrecoMedioHora(Double precoMedioHora) {
		this.precoMedioHora = precoMedioHora;
	}

	@JoinColumn(name = "ENDERECO_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_local_id"))
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@NotNull(message = ENDERECO_OBRIGATORIO)
	public Endereco getEnderecoLocal() {
		return enderecoLocal;
	}

	public void setEnderecoLocal(Endereco enderecoLocal) {
		this.enderecoLocal = enderecoLocal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localAvaliado")
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@JoinTable(name = "horarios_locais", joinColumns = {
			@JoinColumn(name = "LOCAL_ID", table = "locais", referencedColumnName = "ID") }, foreignKey = @ForeignKey(name = "fk_local_hora_id"), inverseJoinColumns = {
					@JoinColumn(name = "HORARIO_DISP_ID", table = "faixas_horarios_disponiveis", referencedColumnName = "ID") }, inverseForeignKey = @ForeignKey(name = "fk_horario_disp_id"))
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<FaixaHorarioDisponivel> getFaixasHorariosDisponiveis() {
		return faixasHorariosDisponiveis;
	}

	public void setFaixasHorariosDisponiveis(List<FaixaHorarioDisponivel> faixasHorariosDisponiveis) {
		this.faixasHorariosDisponiveis = faixasHorariosDisponiveis;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "USUARIO_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_id"))
	@NotNull(message = USUARIO_OBRIGATORIO)
	public Usuario getUsuarioProprietario() {
		return usuarioProprietario;
	}

	public void setUsuarioProprietario(Usuario usuarioProprietario) {
		this.usuarioProprietario = usuarioProprietario;
	}
}
