package com.frigorifico.mendes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Transportadora;
import com.frigorifico.mendes.repository.Transportadoras;
import com.frigorifico.mendes.service.exception.CpfCnpjTransportadoraJaCadastradoException;

@Service
public class CadastroTransportadoraService {

	@Autowired
	private Transportadoras transportadoras;

	@Transactional
	public void salvar(Transportadora transportadora) {
		Optional<Transportadora> transportadoraExistente = transportadoras.findByCpfOuCnpj(transportadora.getCpfOuCnpjSemFormatacao());
		if (transportadoraExistente.isPresent()) {
			throw new CpfCnpjTransportadoraJaCadastradoException("CPF/CNPJ já cadastrado");
		}		
		
		transportadoras.save(transportadora);
	}

}
