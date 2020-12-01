package com.frigorifico.mendes.service.event.veiculo;

import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Veiculo;

public class VeiculoSalvoEvent {
	
	private Veiculo veiculo;

	public VeiculoSalvoEvent(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(veiculo.getFoto());
	}

}
