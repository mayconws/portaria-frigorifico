package com.frigorifico.mendes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.repository.helper.movimentacao.MovimentacoesQueries;

@Repository
public interface Movimentacoes extends JpaRepository<Movimentacao, Long>, MovimentacoesQueries {
	
}
