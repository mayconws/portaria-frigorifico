package com.frigorifico.mendes.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.frigorifico.mendes.model.Movimentacao;

@Component
public class MovimentacaoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Movimentacao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "motorista.codigo", "", "Selecione um motorista na pesquisa rápida");
		
		Movimentacao movimentacao = (Movimentacao) target;
		validarApenasSeInformouDataDaSaida(errors, movimentacao);
//		validarSeInformouUmVeiculo(errors, movimentacao);
		
	}

//	private void validarSeInformouUmVeiculo(Errors errors, Movimentacao movimentacao) {
//		if (movimentacao.getItens().isEmpty()) {
//			errors.reject("", "Informe um veículo na movimentação");
//		}
//	}

	private void validarApenasSeInformouDataDaSaida(Errors errors, Movimentacao movimentacao) {
		if (movimentacao.getDataDaSaida() != null && movimentacao.getHorarioDaSaida() == null) {
			errors.rejectValue("horarioDaSaida", "", "Informe o horário da saída");
		}
	}

}
