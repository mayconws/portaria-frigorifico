//package com.frigorifico.mendes.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "item_veiculo")
//public class ItemVeiculo {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long codigo;
//	
//	private Integer quantidade;
//	
//	@ManyToOne
//	@JoinColumn(name = "codigo_veiculo")
//	private Veiculo veiculo;
//	
//	@ManyToOne
//	@JoinColumn(name = "codigo_movimentacao")
//	private Movimentacao movimentacao;
//	
//	public Long getCodigo() {
//		return codigo;
//	}
//	
//	public void setCodigo(Long codigo) {
//		this.codigo = codigo;
//	}
//	
//	public Integer getQuantidade() {
//		return quantidade;
//	}
//	
//	public void setQuantidade(Integer quantidade) {
//		this.quantidade = quantidade;
//	}
//	
//	public Veiculo getVeiculo() {
//		return veiculo;
//	}
//	
//	public void setVeiculo(Veiculo veiculo) {
//		this.veiculo = veiculo;
//	}	
//	
//	public Movimentacao getMovimentacao() {
//		return movimentacao;
//	}
//
//	public void setMovimentacao(Movimentacao movimentacao) {
//		this.movimentacao = movimentacao;
//	}
//
//	public Integer getQuantidadeVeiculo() {
//		for (int i = 0; i <= quantidade; i++) {
//		
//		}
//		return quantidade;
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ItemVeiculo other = (ItemVeiculo) obj;
//		if (codigo == null) {
//			if (other.codigo != null)
//				return false;
//		} else if (!codigo.equals(other.codigo))
//			return false;
//		return true;
//	}
//
//}
