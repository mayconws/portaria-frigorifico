package com.frigorifico.mendes.dto;

public class MovimentacaoMes {
	
	private String mes;
	private Integer total;

	public MovimentacaoMes() {
	}

	public MovimentacaoMes(String mes, Integer total) {
		this.mes = mes;
		this.total = total;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}	

}
