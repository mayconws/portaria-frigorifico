package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Motorista;
import com.frigorifico.mendes.repository.Motoristas;
import com.frigorifico.mendes.service.exception.MotoristaJaCadastradoException;

@Service
public class CadastroMotoristaService {

	@Autowired
	private Motoristas motoristas;
	
	@Transactional
	public void salvar(Motorista motorista) {
		Optional<Motorista> MotoristaExistente = motoristas.findByCpfIgnoreCase(motorista.getCpf());
		if (MotoristaExistente.isPresent()) {
			throw new MotoristaJaCadastradoException("Motorista já cadastrado");
		}
		
		motoristas.save(motorista);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		motoristas.deleteById(codigo);
	}

}
