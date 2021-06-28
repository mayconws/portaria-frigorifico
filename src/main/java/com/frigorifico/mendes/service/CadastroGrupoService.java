package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Grupo;
import com.frigorifico.mendes.repository.Grupos;
import com.frigorifico.mendes.service.exception.NomeGrupoJaCadastradoException;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private Grupos grupos;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		Optional<Grupo> grupoOptional = grupos.findByNomeIgnoreCase(grupo.getNome());
		if (grupoOptional.isPresent()) {
			throw new NomeGrupoJaCadastradoException("Grupo já cadastrado");
		}
		
		return grupos.saveAndFlush(grupo);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		grupos.deleteById(codigo);
	}

}
