package br.com.ifsp.garageando.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = -695451887754689135L;

	private static final String COMENTARIO_OBRIGATORIO = "ATENÇÃO! É necessário adicionar um comentário à avaliação!";
	private static final String USUARIO_OBRIGATORIO = "ATENÇÃO! É necessário definir o usuário da avaliação!";

	private long id;
	private String comentario;
	private double rating;
	private Usuario usuario;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@NotBlank(message = COMENTARIO_OBRIGATORIO)
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Min(value = 1)
	@Max(value = 5)
	@Digits(fraction = 1, integer = 1)
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@JoinColumn(unique = false)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull(message = USUARIO_OBRIGATORIO)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
