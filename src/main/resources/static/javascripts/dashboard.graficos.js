var Mendes = Mendes || {};

Mendes.GraficoMovimentacaoPorMes = (function() {
	
	function GraficoMovimentacaoPorMes() {
		this.ctx = $('#graficoMovimentacaoPorMes')[0].getContext('2d');
	}
	
	GraficoMovimentacaoPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'controle/veiculos/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(movimentacaoMes) {
		var meses = [];
		var valores = [];
		movimentacaoMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});	
	
		var graficoMovimentacaoPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Veículos por mês',
		    		backgroundColor: "#4e73df",
	                pointBorderColor: "#4e73df",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoMovimentacaoPorMes;
	
}());

$(function() {
	var graficoMovimentacaoPorMes = new Mendes.GraficoMovimentacaoPorMes();
	graficoMovimentacaoPorMes.iniciar();
});