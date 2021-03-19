package com.frigorifico.mendes.repository.helper.veiculo;

import java.util.List;

import com.frigorifico.mendes.dto.VeiculoDTO;

public interface VeiculosQueries {
	
	public List<VeiculoDTO> porPlacaOuModelo(String placaOuModelo);

}
