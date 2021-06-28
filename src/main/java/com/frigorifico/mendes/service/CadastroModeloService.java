package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Modelo;
import com.frigorifico.mendes.repository.Modelos;
import com.frigorifico.mendes.service.exception.NomeModeloJaCadastradoException;

@Service
public class CadastroModeloService {

	@Autowired
	private Modelos modelos;

	@Transactional
	public Modelo salvar(Modelo modelo) {
		Optional<Modelo> modeloOptional = modelos.findByNomeIgnoreCase(modelo.getNome());
		if (modeloOptional.isPresent()) {
			throw new NomeModeloJaCadastradoException("Modelo já cadastrado");
		}
		
		return modelos.saveAndFlush(modelo);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		modelos.deleteById(codigo);
	}

}
