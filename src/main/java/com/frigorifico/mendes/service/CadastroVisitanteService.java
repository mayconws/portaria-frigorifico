package com.frigorifico.mendes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Visitante;
import com.frigorifico.mendes.repository.Visitantes;

@Service
public class CadastroVisitanteService {

	@Autowired
	private Visitantes visitantes;

	@Transactional
	public void salvar(Visitante visitante) {
		visitantes.save(visitante);

	}
	
	@Transactional
	public void excluir(Long codigo) {
		visitantes.delete(codigo);
	}

}
