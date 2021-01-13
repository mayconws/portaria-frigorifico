package com.frigorifico.mendes.service.event.visitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.frigorifico.mendes.storage.FotoStorage;

@Component
public class VisitanteListener {

	@Autowired
	private FotoStorage fotoStorage;

	@EventListener(condition = "#evento.temFoto()")
	public void VisitanteSalvo(VisitanteSalvoEvent evento) {
		fotoStorage.salvar(evento.getVisitante().getFoto());
	}

}
