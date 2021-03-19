Mendes.Veiculo = (function(){
	
	function Veiculo(tabelaItens) {
		this.tabelaItens = tabelaItens;
		this.totalVeiculoBox = $('.js-total-veiculo-box');
	}
	
	Veiculo.prototype.iniciar = function() {
		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));		
	}
	
	function onTabelaItensAtualizada(evento, valorTotalItens) {
		var v = valorTotalItens == null ? 0 : valorTotalItens;
		this.totalVeiculoBox.html(v);
	}	
	
	return Veiculo;
	
}());


$(function() {
	
	var autocomplete = new Mendes.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Mendes.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var veiculo = new Mendes.Veiculo(tabelaItens);
	veiculo.iniciar();
	
})