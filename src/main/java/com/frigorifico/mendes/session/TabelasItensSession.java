package com.frigorifico.mendes.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.frigorifico.mendes.model.ItemVeiculo;
import com.frigorifico.mendes.model.Veiculo;

@SessionScope
@Component
public class TabelasItensSession {
	
	private Set<TabelaItensVeiculo> tabelas = new HashSet<>();

	public void adicionarVeiculo(String uuid, Veiculo veiculo, int quantidade) {
		TabelaItensVeiculo tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarVeiculo(veiculo, quantidade);
		tabelas.add(tabela);
		
	}

	public void alterarQuantidadeItens(String uuid, Veiculo veiculo, Integer quantidade) {
		TabelaItensVeiculo tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItens(veiculo, quantidade);
		
	}

	public void excluirItem(String uuid, Veiculo veiculo) {
		TabelaItensVeiculo tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(veiculo);
		
	}

	public List<ItemVeiculo> getItens(String uuid) {
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensVeiculo buscarTabelaPorUuid(String uuid) {
		TabelaItensVeiculo tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensVeiculo(uuid));
		return tabela;
	}

	public Object getQuantidadeTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getTotalveiculo();
	}

}
