package com.frigorifico.mendes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Visitante;
import com.frigorifico.mendes.repository.Visitantes;
import com.frigorifico.mendes.service.event.visitante.VisitanteSalvoEvent;

@Service
public class CadastroVisitanteService {

	@Autowired
	private Visitantes visitantes;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void salvar(Visitante visitante) {
		visitantes.save(visitante);

		publisher.publishEvent(new VisitanteSalvoEvent(visitante));
	}

}
