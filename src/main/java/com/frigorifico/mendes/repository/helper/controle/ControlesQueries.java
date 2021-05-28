package com.frigorifico.mendes.repository.helper.controle;

import java.util.List;

import com.frigorifico.mendes.dto.MovimentacaoMes;
import com.frigorifico.mendes.model.Movimentacao;

public interface ControlesQueries {
	
public Movimentacao buscarVisitantes(Long codigo);
	
	public Long visitantesTotal();
	
	public Long totalVisitantesNoano();
	
	public Long totalVisitantesNoMes();
	
	public List<MovimentacaoMes> totalPorMes();

}
