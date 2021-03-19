Mendes = Mendes || {};

Mendes.PesquisaRapidaCliente = (function() {
	
	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaMotoristas');
		this.nomeInput = $('#nomeMotoristaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-motoristas-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaMotoristas');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-motorista').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaClientePesquisaRapida = new Mendes.TabelaClientePesquisaRapida(this.pesquisaRapidaClientesModal);
		tabelaClientePesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaCliente;
	
}());

Mendes.TabelaClientePesquisaRapida = (function() {
	
	function TabelaClientePesquisaRapida(modal) {
		this.modalCliente = modal;
		this.cliente = $('.js-motorista-pesquisa-rapida');
	}
	
	TabelaClientePesquisaRapida.prototype.iniciar = function() {
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento) {
		this.modalCliente.modal('hide');
		
		var clienteSelecionado = $(evento.currentTarget);
		$('#nomeMotorista').val(clienteSelecionado.data('nome'));
		$('#codigoMotorista').val(clienteSelecionado.data('codigo'));
	}
	
	return TabelaClientePesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaCliente = new Mendes.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
});