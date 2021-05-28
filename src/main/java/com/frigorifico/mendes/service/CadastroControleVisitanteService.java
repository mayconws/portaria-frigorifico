package com.frigorifico.mendes.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Controle;
import com.frigorifico.mendes.model.StatusVisitante;
import com.frigorifico.mendes.repository.Controles;

@Service
public class CadastroControleVisitanteService {
	
	@Autowired
	private Controles controles;

	@Transactional
	public void salvar(Controle controleVisitante) {
		if (controleVisitante.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma movimentacão proibida");
		}
		
		if (controleVisitante.isNovo()) {
			controleVisitante.setDataEntrada(LocalDateTime.now());
			
		} else {
			Controle controleExistente = controles.findOne(controleVisitante.getCodigo());
			controleVisitante.setDataEntrada(controleExistente.getDataEntrada());
		}
		
//		if (controleVisitante.getDataDaSaida() != null) {
//			controleVisitante.setDataSaida(LocalDateTime.of(controleVisitante.getDataDaSaida(), 
//					controleVisitante.getHorarioDaSaida() != null ? controleVisitante.getHorarioDaSaida() : LocalTime.NOON));
//		}
		
		controles.save(controleVisitante);
	}
	
	@Transactional
	public void lancar(Controle controle) {
		controle.setStatus(StatusVisitante.OPERACAO);
		salvar(controle);		
	}
	
	@Transactional
	public void finalizar(Controle controle) {
		controle.setStatus(StatusVisitante.FINALIZADO);
		salvar(controle);		
	}
	
	@PreAuthorize("#controle.usuario == principal.usuario or hasRole('CANCELAR_CONTROLE')")
	@Transactional
	public void cancelar(Controle controle) {
		Controle controleExistente = controles.findOne(controle.getCodigo());
		
		controleExistente.setStatus(StatusVisitante.CANCELADO);
		controles.save(controleExistente);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		controles.delete(codigo);
	}

}
