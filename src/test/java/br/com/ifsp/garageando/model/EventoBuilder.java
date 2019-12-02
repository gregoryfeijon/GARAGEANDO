package br.com.ifsp.garageando.model;

import java.time.LocalDate;

/**
 * 1 de dez de 2019
 *
 * @author gregory.feijon
 */

public class EventoBuilder {

	private Evento evento = new Evento();

	public void novo() {
		this.evento = new Evento();
	}

	public Evento build() {
		return evento;
	}

	public EventoBuilder withNome(String nome) {
		evento.setNome(nome);
		return this;
	}

	public EventoBuilder withPreco(Double preco) {
		evento.setPreco(preco);
		return this;
	}

	public EventoBuilder withData(LocalDate data) {
		evento.setData(data);
		return this;
	}

	public EventoBuilder withNumero(int numero) {
		evento.setNumero(numero);
		return this;
	}

	public EventoBuilder withComplemento(String complemento) {
		evento.setComplemento(complemento);
		return this;
	}

	public EventoBuilder withEnderecoEvento(Endereco endereco) {
		evento.setEnderecoEvento(endereco);
		return this;
	}

	public EventoBuilder withUsuarioResponsavelEvento(Usuario usuarioResponsavelEvento) {
		evento.setUsuarioResponsavelEvento(usuarioResponsavelEvento);
		return this;
	}
}
