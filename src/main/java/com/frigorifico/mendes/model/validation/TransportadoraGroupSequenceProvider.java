package com.frigorifico.mendes.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.frigorifico.mendes.model.Transportadora;

public class TransportadoraGroupSequenceProvider implements DefaultGroupSequenceProvider<Transportadora> {
	
	@Override
	public List<Class<?>> getValidationGroups(Transportadora transportadora) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Transportadora.class);
		
		if (isPessoaSelecionada(transportadora)) {
			grupos.add(transportadora.getTipoPessoa().getGrupo());
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Transportadora transportadora) {
		return transportadora != null && transportadora.getTipoPessoa() != null;
	}

}
