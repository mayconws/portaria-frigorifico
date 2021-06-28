package com.frigorifico.mendes.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Transportadora;

@Component
public class TransportadoraConverter implements Converter<String, Transportadora> {

	@Override
	public Transportadora convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Transportadora transportadora = new Transportadora();
			transportadora.setCodigo(Long.valueOf(codigo));
			return transportadora;
		}
		
		return null;
	}

}
