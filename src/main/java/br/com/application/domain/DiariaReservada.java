package br.com.application.domain;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@DiscriminatorValue("reservada")
public class DiariaReservada extends Diaria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Reserva reserva;

	public DiariaReservada() {
		super();
	}

	@ManyToOne
	@JoinColumn(name="cod_reserva")
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
}
