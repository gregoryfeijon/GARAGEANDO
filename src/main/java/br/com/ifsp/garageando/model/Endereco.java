package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ifsp.garageando.enums.Estado;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "enderecos")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"locais", "eventos"})
public class Endereco implements Serializable {

	private static final long serialVersionUID = 4830439413890041038L;

	private static final String CIDADE_OBRIGATORIO = "ATENÇÃO! O campo cidade é OBRIGATÓRIO!";
	private static final String BAIRRO_OBRIGATORIO = "ATENÇÃO! O campo bairro é OBRIGATÓRIO!";
	private static final String RUA_OBRIGATORIO = "ATENÇÃO! O campo rua é OBRIGATÓRIO!";
	private static final String CEP_INVALIDO = "ATENÇÃO! O número de CEP inserido é inválido!";

	private Long id;
	private String cidade;
	private String bairro;
	private String rua;
	private int cep;
	private String latitude;
	private String longitude;
	private Estado estado;
	private List<Evento> eventos;
	private List<Local> locais;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = CIDADE_OBRIGATORIO)
	@Column(name = "CIDADE", nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank(message = BAIRRO_OBRIGATORIO)
	@Column(name = "BAIRRO", nullable = false)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank(message = RUA_OBRIGATORIO)
	@Column(name = "RUA", nullable = false)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Digits(fraction = 0, integer = 8, message = CEP_INVALIDO)
	@Column(name = "CEP", nullable = false)
	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
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
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enderecoLocal")
	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}
}
