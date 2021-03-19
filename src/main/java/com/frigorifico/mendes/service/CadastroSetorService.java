package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Setor;
import com.frigorifico.mendes.repository.Setores;
import com.frigorifico.mendes.service.exception.NomeSetorJaCadastradoException;

@Service
public class CadastroSetorService {

	@Autowired
	private Setores setores;

	@Transactional
	public Setor salvar(Setor setor) {
		Optional<Setor> setorOptional = setores.findByNomeIgnoreCase(setor.getNome());
		if (setorOptional.isPresent()) {
			throw new NomeSetorJaCadastradoException("Setor já cadastrado");
		}
		
		return setores.saveAndFlush(setor);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		setores.delete(codigo);
	}

}
