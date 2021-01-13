package com.frigorifico.mendes.service.event.visitante;

import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Visitante;

public class VisitanteSalvoEvent {
	
	private Visitante visitante;

	public VisitanteSalvoEvent(Visitante visitante) {
		this.visitante = visitante;
	}

	public Visitante getVisitante() {
		return visitante;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(visitante.getFoto());
	}

}
