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

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 4830439413890041038L;

	private static final String CIDADE_OBRIGATORIO = "ATENÇÃO! o campo cidade é OBRIGATÓRIO!";
	private static final String BAIRRO_OBRIGATORIO = "ATENÇÃO! o campo bairro é OBRIGATÓRIO!";
	private static final String RUA_OBRIGATORIO = "ATENÇÃO! o campo rua é OBRIGATÓRIO!";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = CIDADE_OBRIGATORIO)
	private String cidade;

	@NotBlank(message = BAIRRO_OBRIGATORIO)
	private String bairro;

	@NotBlank(message = RUA_OBRIGATORIO)
	private String rua;

	@Digits(fraction = 0, integer = 8)
	private int cep;

	private String latitude;
	private String longitude;
	
	@Enumerated(EnumType.ORDINAL)
	private Estado estado;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
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
