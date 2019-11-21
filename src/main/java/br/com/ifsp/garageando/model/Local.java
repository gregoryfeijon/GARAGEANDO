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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "locais")
public class Local implements Serializable {

	private static final long serialVersionUID = -903758268377656191L;

	private static final String PRECO_MEDIO_OBRIGATORIO = "ATENÇÃO! O campo preço médio é OBRIGATÓRIO!";
	public static final String ENDERECO_OBRIGATORIO = "ATENÇÃO! é OBRIGATÓRIO que um local possua um endereço!";

	private long id;
	private Double largura;
	private Double altura;
	private boolean isDisponivel;
	private Double precoMedioHora;
	private int numero;
	private Endereco enderecoLocal;
	private List<Avaliacao> avaliacoes;
	private List<FaixaHorarioDisponivel> faixasHorariosDisponiveis;
	private Usuario usuarioProprietario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	@Column(name = "DISPONIVEL")
	public boolean isDisponivel() {
		return isDisponivel;
	}

	public void setDisponivel(boolean isDisponivel) {
		this.isDisponivel = isDisponivel;
	}

	@NotBlank(message = PRECO_MEDIO_OBRIGATORIO)
	@Column(name = "PRECO_MEDIO_HORA")
	public Double getPrecoMedioHora() {
		return precoMedioHora;
	}

	public void setPrecoMedioHora(Double precoMedioHora) {
		this.precoMedioHora = precoMedioHora;
	}

	@JoinColumn(name = "ENDERECO_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_endereco_local_id"))
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@NotNull(message = ENDERECO_OBRIGATORIO)
	public Endereco getEnderecoLocal() {
		return enderecoLocal;
	}

	public void setEnderecoLocal(Endereco enderecoLocal) {
		this.enderecoLocal = enderecoLocal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "localAvaliado", cascade = CascadeType.ALL)
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	@JoinTable(name = "horarios_locais", joinColumns = {
			@JoinColumn(name = "LOCAL_ID", table = "locais", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "HORARIO_DISP_ID", table = "faixas_horarios_disponiveis", referencedColumnName = "ID") })
	@ManyToMany(fetch = FetchType.EAGER)
	public List<FaixaHorarioDisponivel> getFaixasHorariosDisponiveis() {
		return faixasHorariosDisponiveis;
	}

	public void setFaixasHorariosDisponiveis(List<FaixaHorarioDisponivel> faixasHorariosDisponiveis) {
		this.faixasHorariosDisponiveis = faixasHorariosDisponiveis;
	}

	@JoinColumn(name = "USUARIO_ID", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_usuario_prop_id"))
	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuarioProprietario() {
		return usuarioProprietario;
	}

	public void setUsuarioProprietario(Usuario usuarioProprietario) {
		this.usuarioProprietario = usuarioProprietario;
	}
}
