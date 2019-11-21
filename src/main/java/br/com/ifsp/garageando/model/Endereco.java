package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import br.com.ifsp.garageando.enums.Estado;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 4830439413890041038L;

	private static final String CIDADE_OBRIGATORIO = "ATENÇÃO! O campo cidade é OBRIGATÓRIO!";
	private static final String BAIRRO_OBRIGATORIO = "ATENÇÃO! O campo bairro é OBRIGATÓRIO!";
	private static final String RUA_OBRIGATORIO = "ATENÇÃO! O campo rua é OBRIGATÓRIO!";
	private static final String CEP_INVALIDO = "ATENÇÃO! O número de CEP inserido é inválido!";

	private long id;
	private String cidade;
	private String bairro;
	private String rua;
	private int cep;
	private Estado estado;
	private String latitude;
	private String longitude;
	private Evento evento;
	private Local local;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = CIDADE_OBRIGATORIO)
	@Column(name = "CIDADE")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank(message = BAIRRO_OBRIGATORIO)
	@Column(name = "BAIRRO")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank(message = RUA_OBRIGATORIO)
	@Column(name = "RUA")
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Digits(fraction = 0, integer = 8, message = CEP_INVALIDO)
	@Column(name = "CEP")
	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO")
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enderecoEvento")
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enderecoLocal")
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}
}
