package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import br.com.ifsp.garageando.enums.Estado;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 4830439413890041038L;

	private static final String CIDADE_OBRIGATORIO = "ATENÇÃO! o campo cidade é OBRIGATÓRIO!";
	private static final String BAIRRO_OBRIGATORIO = "ATENÇÃO! o campo bairro é OBRIGATÓRIO!";
	private static final String RUA_OBRIGATORIO = "ATENÇÃO! o campo rua é OBRIGATÓRIO!";

	private long id;
	private String cidade;
	private String bairro;
	private String rua;
	private int cep;
	private Estado estado;
	private String latitude;
	private String longitude;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = CIDADE_OBRIGATORIO)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank(message = BAIRRO_OBRIGATORIO)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank(message = RUA_OBRIGATORIO)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Digits(fraction = 0, integer = 8)
	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	@Enumerated(EnumType.ORDINAL)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
