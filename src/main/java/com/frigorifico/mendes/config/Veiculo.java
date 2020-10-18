package com.frigorifico.mendes.config;

import org.hibernate.validator.constraints.NotBlank;

public class Veiculo {
	
	@NotBlank(message = "A placa é obrigatório!")
	public String placa;
	
	@NotBlank(message = "O modelo é obrigatório!")
	public String modelo;		

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}	

}
