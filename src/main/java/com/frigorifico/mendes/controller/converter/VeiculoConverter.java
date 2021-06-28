package com.frigorifico.mendes.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Veiculo;

@Component
public class VeiculoConverter implements Converter<String, Veiculo> {
	
	@Override
	public Veiculo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Veiculo veiculo = new Veiculo();
			veiculo.setCodigo(Long.valueOf(codigo));
			return veiculo;
		}
		
		return null;
	}	

}
