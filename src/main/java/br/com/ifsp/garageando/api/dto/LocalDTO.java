package br.com.ifsp.garageando.api.dto;

import java.util.List;

import br.com.ifsp.garageando.model.Endereco;
import br.com.ifsp.garageando.model.Local;
import br.com.ifsp.garageando.model.Usuario;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

public class LocalDTO {

	private Long id;
	private Usuario usuario;
	private Endereco endereco;
	private Local local;
	private List<Local> locais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

}
