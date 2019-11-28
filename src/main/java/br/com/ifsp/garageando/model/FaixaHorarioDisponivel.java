package br.com.ifsp.garageando.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifsp.garageando.enums.DiaSemana;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

@Entity
@Table(name = "faixas_horarios_disponiveis")
public class FaixaHorarioDisponivel implements Serializable {

	private static final long serialVersionUID = 769585940406699350L;

	private static final String HORARIO_INICIO_OBRIGATORIO = "ATENÇÃO! O horário de início da disponibilidade do local é OBRIGATÓRIO!";
	private static final String HORARIO_FIM_OBRIGATORIO = "ATENÇÃO! O horário de fim da disponibilidade do local é OBRIGATÓRIO!";
	private static final String DIA_SEMANA_INICIO_OBRIGATORIO = "ATENÇÃO! O dia inicial da semana em que o local está disponível naquela faixa de horário é OBRIGATÓRIO!";
	private static final String DIA_SEMANA_FIM_OBRIGATORIO = "ATENÇÃO! O dia final da semana em que o local está disponível naquela faixa de horário é OBRIGATÓRIO!";

	private long id;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;
	private DiaSemana diaDaSemanaInicio;
	private DiaSemana diaDaSemanaFim;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonFormat(pattern = "HH:mm:ss.SSS")
	@NotBlank(message = HORARIO_INICIO_OBRIGATORIO)
	@Column(name = "HORARIO_INICIO", nullable = false)
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	@JsonFormat(pattern = "HH:mm:ss.SSS")
	@NotBlank(message = HORARIO_FIM_OBRIGATORIO)
	@Column(name = "HORARIO_FIM", nullable = false)
	public LocalTime getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(LocalTime horarioFim) {
		this.horarioFim = horarioFim;
	}

	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = DIA_SEMANA_INICIO_OBRIGATORIO)
	@Column(name = "DIA_SEMANA_INICIO", nullable = false)
	public DiaSemana getDiaDaSemanaInicio() {
		return diaDaSemanaInicio;
	}

	public void setDiaDaSemanaInicio(DiaSemana diaDaSemanaInicio) {
		this.diaDaSemanaInicio = diaDaSemanaInicio;
	}

	@Enumerated(EnumType.ORDINAL)
	@NotNull(message = DIA_SEMANA_FIM_OBRIGATORIO)
	@Column(name = "DIA_SEMANA_FIM", nullable = false)
	public DiaSemana getDiaDaSemanaFim() {
		return diaDaSemanaFim;
	}

	public void setDiaDaSemanaFim(DiaSemana diaDaSemanaFim) {
		this.diaDaSemanaFim = diaDaSemanaFim;
	}
}
