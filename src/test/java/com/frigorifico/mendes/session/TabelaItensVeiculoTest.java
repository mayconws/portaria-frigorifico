package com.frigorifico.mendes.session;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.frigorifico.mendes.model.Veiculo;
import com.frigorifico.mendes.session.TabelaItensVeiculo;

public class TabelaItensVeiculoTest {
	
	private TabelaItensVeiculo tabelaItensVeiculo;
	
	@Before
	public void setUp() {
		this.tabelaItensVeiculo = new TabelaItensVeiculo("1");
	}
	
	@Test
	public void calcularTotalDeVeiculos() throws Exception {
		tabelaItensVeiculo.getTotalveiculo();
		
		assertEquals(new Integer(0), tabelaItensVeiculo.getTotalveiculo());
		System.out.println(tabelaItensVeiculo.getTotalveiculo());
		
	}
	
	@Test
	public void calcularVeiculosAdionados() throws Exception {
		Veiculo v1 = new Veiculo();
		v1.setCodigo(1L);
		Veiculo v2 = new Veiculo();	
		v2.setCodigo(1L);
		
		tabelaItensVeiculo.adicionarVeiculo(v1, 1);
		tabelaItensVeiculo.adicionarVeiculo(v2, 1);
		
		assertEquals(new Integer(2), tabelaItensVeiculo.getTotalveiculo());
		System.out.println(tabelaItensVeiculo.getTotalveiculo());	
		
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmosVeiculos() throws Exception {
		Veiculo v1 = new Veiculo();
		v1.setCodigo(1L);
		
		tabelaItensVeiculo.adicionarVeiculo(v1, 1);
		tabelaItensVeiculo.adicionarVeiculo(v1, 1);
		
		assertEquals(1, tabelaItensVeiculo.total());
		assertEquals(new Integer(2), tabelaItensVeiculo.getTotalveiculo());
	}
	
	@Test
	public void deveAlterarQuantidadeDoItem() throws Exception {
		Veiculo v1 = new Veiculo();
		v1.setCodigo(1L);
		
		
		tabelaItensVeiculo.adicionarVeiculo(v1, 1);
		tabelaItensVeiculo.alterarQuantidadeItens(v1, 3);
		
		assertEquals(1, tabelaItensVeiculo.total());
		assertEquals(new Integer(3), tabelaItensVeiculo.getTotalveiculo());
	}
	
	@Test
	public void deveExcluirItem() throws Exception {
		Veiculo v1 = new Veiculo();
		v1.setCodigo(1L);
		
		Veiculo v2 = new Veiculo();
		v2.setCodigo(2L);
		
		Veiculo v3 = new Veiculo();
		v3.setCodigo(3L);
		
		tabelaItensVeiculo.adicionarVeiculo(v1, 1);
		tabelaItensVeiculo.adicionarVeiculo(v2, 2);
		tabelaItensVeiculo.adicionarVeiculo(v3, 1);
		
		tabelaItensVeiculo.excluirItem(v2);
		
		assertEquals(2, tabelaItensVeiculo.total());
		assertEquals(new Integer(2), tabelaItensVeiculo.getTotalveiculo());
	}

}
