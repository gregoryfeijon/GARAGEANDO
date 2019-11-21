package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

/**
 * 10 de nov de 2019
 *
 * @author gregory.feijon
 */

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaFisica extends Pessoa implements Serializable {

	private static final long serialVersionUID = -3134130963471833905L;

	private static final String CPF_OBRIGATORIO = "Atenção! O campo CPF é OBRIGATÓRIO!";
	private static final String CPF_INVALIDO = "CPF inválido!!!";

	private int cpf;
	private Usuario usuario;
	private List<PessoaJuridica> empresas;

	@CPF(message = CPF_INVALIDO)
	@NotBlank(message = CPF_OBRIGATORIO)
	@Digits(fraction = 0, integer = 11, message = CPF_INVALIDO)
	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID", nullable = false, unique = true, foreignKey = @ForeignKey(foreignKeyDefinition = "fnk_usuario_id"))
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToMany(mappedBy = "pessoaFisica", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<PessoaJuridica> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<PessoaJuridica> empresas) {
		this.empresas = empresas;
	}
}
