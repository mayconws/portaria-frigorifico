package com.frigorifico.mendes.model;

public enum SituacaoVeiculo {
	
	VAZIO("Vazio"), 
	CARREGADO("Carregado");

	private String descricao;

	SituacaoVeiculo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
