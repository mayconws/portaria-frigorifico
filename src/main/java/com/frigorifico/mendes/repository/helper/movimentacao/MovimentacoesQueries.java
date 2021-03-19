package com.frigorifico.mendes.repository.helper.movimentacao;

import java.util.List;

import com.frigorifico.mendes.dto.MovimentacaoMes;
import com.frigorifico.mendes.model.Movimentacao;

public interface MovimentacoesQueries {
	
	public Movimentacao buscarVeiculos(Long codigo);
	
	public Long veiculosTotal();
	
	public Long totalVeiculosNoano();
	
	public Long totalVeiculosNoMes();
	
	public List<MovimentacaoMes> totalPorMes();

}
