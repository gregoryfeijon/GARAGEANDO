package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

/**
 * 10 de nov de 2019
 *
 * @author gregory.feijon
 */

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaJuridica extends Pessoa implements Serializable {

	private static final long serialVersionUID = -1598649510122761952L;

	private static final String CNPJ_OBRIGATORIO = "Atenção! O campo CNPJ é OBRIGATÓRIO!";
	private static final String CNPJ_INVALIDO = "CNPJ inválido!!!";

	private int cnpj;
	private PessoaFisica pessoaFisica;

	@CNPJ(message = CNPJ_INVALIDO)
	@NotBlank(message = CNPJ_OBRIGATORIO)
	@Digits(fraction = 0, integer = 14, message = CNPJ_INVALIDO)
	@Column(name = "CNPJ")
	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PESSOA_FISICA_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_pessoa_fisica_id"))
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
}
