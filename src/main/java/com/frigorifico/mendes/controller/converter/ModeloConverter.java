package com.frigorifico.mendes.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Modelo;

public class ModeloConverter implements Converter<String, Modelo> {
	
	@Override
	public Modelo convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Modelo modelo = new Modelo();
			modelo.setCodigo(Long.valueOf(codigo));
			return modelo;
		}
		
		return null;
	}	

}
