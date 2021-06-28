package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Visitante;
import com.frigorifico.mendes.repository.Visitantes;
import com.frigorifico.mendes.service.exception.VisitanteJaCadastradoException;

@Service
public class CadastroVisitanteService {

	@Autowired
	private Visitantes visitantes;

	@Transactional
	public void salvar(Visitante visitante) {
		Optional<Visitante> VisitanteExistente = visitantes.findByCpfIgnoreCase(visitante.getCpf());
		if (VisitanteExistente.isPresent()) {
			throw new VisitanteJaCadastradoException("Visitante já cadastrado");
		}

		visitantes.save(visitante);

	}

	@Transactional
	public void excluir(Long codigo) {
		visitantes.deleteById(codigo);
	}

}
