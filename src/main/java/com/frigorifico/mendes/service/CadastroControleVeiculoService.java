package com.frigorifico.mendes.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frigorifico.mendes.model.Movimentacao;
import com.frigorifico.mendes.model.StatusVeiculo;
import com.frigorifico.mendes.repository.Movimentacoes;

@Service
public class CadastroControleVeiculoService {
	
	@Autowired
	private Movimentacoes movimentacoes;

	@Transactional
	public void salvar(Movimentacao controleVeiculo) {
		if (controleVeiculo.isSalvarProibido()) {
			throw new RuntimeException("Usuário tentando salvar uma movimentação proibida");
		}
		
		if (controleVeiculo.isNova()) {
			controleVeiculo.setDataChegada(LocalDateTime.now());
			
		} else {
			Movimentacao movimentacaoExistente = movimentacoes.getOne(controleVeiculo.getCodigo());
			controleVeiculo.setDataChegada(movimentacaoExistente.getDataChegada());
		}
		
		movimentacoes.save(controleVeiculo);
	}
	
	@Transactional
	public void lancar(Movimentacao movimentacao) {
		movimentacao.setStatus(StatusVeiculo.OPERACAO);
		salvar(movimentacao);		
	}
	
	@Transactional
	public void finalizar(Movimentacao movimentacao) {
		movimentacao.setStatus(StatusVeiculo.FINALIZADO);
		salvar(movimentacao);		
	}
	
	@PreAuthorize("#movimentacao.usuario == principal.usuario or hasRole('CANCELAR_MOVIMENTACAO')")
	@Transactional
	public void cancelar(Movimentacao movimentacao) {
		Movimentacao movimentacaoExistente = movimentacoes.getOne(movimentacao.getCodigo());
		
		movimentacaoExistente.setStatus(StatusVeiculo.CANCELADO);
		movimentacoes.save(movimentacaoExistente);
	}
	
	@Transactional
	public void excluir(Long codigo) {
		movimentacoes.deleteById(codigo);
	}

}
