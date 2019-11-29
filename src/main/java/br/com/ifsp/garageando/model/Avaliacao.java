package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "avaliacoes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = -695451887754689135L;

	private static final String COMENTARIO_OBRIGATORIO = "ATENÇÃO! É necessário adicionar um comentário à avaliação!";
	private static final String USUARIO_OBRIGATORIO = "ATENÇÃO! É necessário definir o usuário da avaliação!";
	private static final String RATING_INVALIDO = "ATENÇÃO! O valor inserido para avaliar o local é inválido!";

	private long id;
	private String comentario;
	private double rating;
	private Usuario usuarioAvaliador;
	private Local localAvaliado;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Lob
	@NotBlank(message = COMENTARIO_OBRIGATORIO)
	@Column(name = "COMENTARIO", nullable = false)
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Min(value = 1, message = RATING_INVALIDO)
	@Max(value = 5, message = RATING_INVALIDO)
	@Digits(fraction = 1, integer = 1, message = RATING_INVALIDO)
	@Column(name = "RATING", nullable = false)
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "USUARIO_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_usuario_id"))
	@NotNull(message = USUARIO_OBRIGATORIO)
	public Usuario getUsuarioAvaliador() {
		return usuarioAvaliador;
	}

	public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
		this.usuarioAvaliador = usuarioAvaliador;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "LOCAL_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_local_id"))
	public Local getLocalAvaliado() {
		return localAvaliado;
	}

	public void setLocalAvaliado(Local localAvaliado) {
		this.localAvaliado = localAvaliado;
	}
}
