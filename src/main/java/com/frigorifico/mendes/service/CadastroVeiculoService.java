package com.frigorifico.mendes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.repository.Veiculos;
import com.frigorifico.mendes.service.event.veiculo.VeiculoSalvoEvent;

@Service
public class CadastroVeiculoService {

	@Autowired
	private Veiculos veiculos;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Transactional
	public void salvar(Veiculo veiculo) {
		veiculos.save(veiculo);

		publisher.publishEvent(new VeiculoSalvoEvent(veiculo));
	}

}
