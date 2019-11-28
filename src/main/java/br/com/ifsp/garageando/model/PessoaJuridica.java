package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

/**
 * 10 de nov de 2019
 *
 * @author gregory.feijon
 */

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable {

	private static final long serialVersionUID = -1598649510122761952L;

	private static final String CNPJ_OBRIGATORIO = "Atenção! O campo CNPJ é OBRIGATÓRIO!";
	private static final String CNPJ_INVALIDO = "CNPJ inválido!!!";

	private String cnpj;
	private PessoaFisica pessoaFisica;

	@CNPJ(message = CNPJ_INVALIDO)
	@NotBlank(message = CNPJ_OBRIGATORIO)
	@Column(name = "CNPJ", nullable = false, unique = true)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "PESSOA_FISICA_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_fisica_id"))
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
}
