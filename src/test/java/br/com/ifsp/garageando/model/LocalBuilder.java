package br.com.ifsp.garageando.model;

import java.util.List;

/**
 * 30 de nov de 2019
 *
 * @author gregory.feijon
 */

public class LocalBuilder {

	private Local local = new Local();

	public void novo() {
		this.local = new Local();
	}

	public Local build() {
		return local;
	}

	public LocalBuilder withLargura(Double largura) {
		local.setLargura(largura);
		return this;
	}

	public LocalBuilder withAltura(Double altura) {
		local.setAltura(altura);
		return this;
	}

	public LocalBuilder withPrecoMedioHora(Double precoMedioHora) {
		local.setPrecoMedioHora(precoMedioHora);
		return this;
	}

	public LocalBuilder withNumero(int numero) {
		local.setNumero(numero);
		return this;
	}

	public LocalBuilder withEnderecoLocal(Endereco enderecoLocal) {
		local.setEnderecoLocal(enderecoLocal);
		return this;
	}

	public LocalBuilder withAvaliacoes(List<Avaliacao> avaliacoes) {
		local.setAvaliacoes(avaliacoes);
		return this;
	}

	public LocalBuilder withFaixasHorariosDisponiveis(List<FaixaHorarioDisponivel> faixasHorariosDisponiveis) {
		local.setFaixasHorariosDisponiveis(faixasHorariosDisponiveis);
		return this;
	}

	public LocalBuilder withUsuarioProprietario(Usuario usuarioProprietario) {
		local.setUsuarioProprietario(usuarioProprietario);
		return this;
	}
}