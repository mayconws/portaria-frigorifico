package com.frigorifico.mendes.model;

public enum StatusVisitante {
	
	AGUARDANDO("Aguardando"), 
	OPERACAO("Em Visita"), 
	FINALIZADO("Finalizado"),
	CANCELADO("Cancelado");

	private String descricao;

	StatusVisitante(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
