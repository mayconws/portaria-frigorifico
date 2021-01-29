package com.frigorifico.mendes.repository.helper.transportadora;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.frigorifico.mendes.model.Transportadora;
import com.frigorifico.mendes.repository.filter.TransportadoraFilter;

public interface TransportadorasQueries {
	
	public Page<Transportadora> filtrar(TransportadoraFilter filtro, Pageable pageable);	

}
