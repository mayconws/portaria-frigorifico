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
		var valores = [1,2,3,4,5,6,7,8,9,10];
		movimentacaoMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});	
	
		var graficoMovimentacaoPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Veículos no dia',
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

Mendes.GraficoControlePorMes = (function() {
	
	function GraficoControlePorMes() {
		this.ctx = $('#graficoControlePorMes')[0].getContext('2d');
	}
	
	GraficoControlePorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'controle/visitantes/totalPorMes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(controleMes) {
		var meses = [];
		var valores = [1,2,3,4,5,6,7,8,9,10];
		controleMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});	
	
		var graficoControlePorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Visitantes no dia',
		    		backgroundColor: "#36b9cc",
	                pointBorderColor: "#4e73df",
	                pointBackgroundColor: "#fff",
	                data: valores
		    	}]
		    },
		});
	}
	
	return GraficoControlePorMes;
	
}());

$(function() {
	
	var graficoMovimentacaoPorMes = new Mendes.GraficoMovimentacaoPorMes();
	graficoMovimentacaoPorMes.iniciar();
	
	var graficoControlePorMes = new Mendes.GraficoControlePorMes();
	graficoControlePorMes.iniciar();
	
});