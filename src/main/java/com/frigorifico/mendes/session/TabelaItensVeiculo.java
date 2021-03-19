package com.frigorifico.mendes.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.frigorifico.mendes.model.ItemVeiculo;
import com.frigorifico.mendes.model.Veiculo;

class TabelaItensVeiculo {
	
	private String uuid;
	
	private List<ItemVeiculo> itens = new ArrayList<>();
	
	public TabelaItensVeiculo(String uuid) {
		this.uuid = uuid;
	}
	
	public Integer getTotalveiculo() {
		return itens.stream().map(ItemVeiculo::getQuantidadeVeiculo)
				.reduce(0, Integer::sum);
		
	}
	
	public void adicionarVeiculo(Veiculo veiculo, Integer quantidade) {
		Optional<ItemVeiculo> itemVeiculoOptional = buscarItemPorVeiculo(veiculo);

		ItemVeiculo itemVeiculo = null;

		if (itemVeiculoOptional.isPresent()) {
			itemVeiculo = itemVeiculoOptional.get();
			itemVeiculo.setQuantidade(itemVeiculo.getQuantidade() + quantidade);

		} else {
			itemVeiculo = new ItemVeiculo();
			itemVeiculo.setVeiculo(veiculo);
			itemVeiculo.setQuantidade(quantidade);
			itens.add(0, itemVeiculo);
		}

	}	
	
	public void alterarQuantidadeItens(Veiculo veiculo, Integer quantidade) {
		ItemVeiculo itemVeiculo = buscarItemPorVeiculo(veiculo).get();
		itemVeiculo.setQuantidade(quantidade);
	}
	
	public void excluirItem(Veiculo veiculo) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getVeiculo().equals(veiculo))
				.findAny().getAsInt();
		itens.remove(indice);
	}

	public int total() {
		return itens.size();
	}

	public List<ItemVeiculo> getItens() {
		return itens;
	}
	
	private Optional<ItemVeiculo> buscarItemPorVeiculo(Veiculo veiculo) {
		return itens.stream()
				.filter(i -> i.getVeiculo().equals(veiculo))
				.findAny();
	}
	
	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVeiculo other = (TabelaItensVeiculo) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
}
