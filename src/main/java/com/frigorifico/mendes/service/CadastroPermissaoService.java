package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Permissao;
import com.frigorifico.mendes.repository.Permissoes;
import com.frigorifico.mendes.service.exception.NomePermissaoJaCadastradoException;

@Service
public class CadastroPermissaoService {

	@Autowired
	private Permissoes permissoes;

	@Transactional
	public Permissao salvar(Permissao permissao) {
		Optional<Permissao> permissaoOptional = permissoes.findByNomeIgnoreCase(permissao.getNome());
		if (permissaoOptional.isPresent()) {
			throw new NomePermissaoJaCadastradoException("Permissão já cadastrada");
		}
		
		return permissoes.saveAndFlush(permissao);
	}

}
