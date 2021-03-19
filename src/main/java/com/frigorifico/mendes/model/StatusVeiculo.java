package com.frigorifico.mendes.model;

public enum StatusVeiculo {
	
	AGUARDANDO("Aguardando"), 
	OPERACAO("Em Operação"), 
	FINALIZADO("Finalizado"),
	CANCELADO("Cancelado");

	private String descricao;

	StatusVeiculo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
