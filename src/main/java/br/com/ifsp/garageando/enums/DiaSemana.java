package br.com.ifsp.garageando.enums;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public enum DiaSemana {
	
	DOMINGO(1) {
		public DiaSemana next() {
			return SEGUNDA;
		}
	},
	SEGUNDA(2) {
		public DiaSemana next() {
			return TERÇA;
		}
	},
	TERÇA(3) {
		public DiaSemana next() {
			return QUARTA;
		}
	},
	QUARTA(4) {
		public DiaSemana next() {
			return QUINTA;
		}
	},
	QUINTA(5) {
		public DiaSemana next() {
			return SEXTA;
		}
	},
	SEXTA(6) {
		public DiaSemana next() {
			return SÁBADO;
		}
	},
	SÁBADO(7) {
		public DiaSemana next() {
			return DOMINGO;
		}
	};

	public abstract DiaSemana next();

	private final int dayNumber;

	DiaSemana(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	int getDayNumber() {
		return dayNumber;
	}
}
