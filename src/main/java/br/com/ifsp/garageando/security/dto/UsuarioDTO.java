package br.com.ifsp.garageando.security.dto;

import br.com.ifsp.garageando.util.StringUtil;

/**
 * 6 de nov de 2019
 *
 * @author gregory.feijon
 */
public class UsuarioDTO {
	
	private String usuario;
	private String senha;
	private String criadoPor;
	private String alteradoPor;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getAlteradoPor() {
		return alteradoPor;
	}

	public void setAlteradoPor(String alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [usuario=" + usuario + ", senha=" + (StringUtil.isNotNull(senha) ? "****" : "nula")
				+ ", criadoPor= " + criadoPor + ", alteradoPor= " + alteradoPor + "]";
	}
}
