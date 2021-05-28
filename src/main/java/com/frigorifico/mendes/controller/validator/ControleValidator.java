package com.frigorifico.mendes.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.frigorifico.mendes.model.Controle;

@Component
public class ControleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Controle.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "visitante.codigo", "", "O visitante é obrigatório");
		
		Controle controle = (Controle) target;
		validarApenasSeInformouDataDaSaida(errors, controle);
		
	}
		

	private void validarApenasSeInformouDataDaSaida(Errors errors, Controle controle) {
		if (controle.getDataDaSaida() != null && controle.getHorarioDaSaida() == null) {
			errors.rejectValue("horarioDaSaida", "", "Informe o horário da saída");
		}
	}

}
