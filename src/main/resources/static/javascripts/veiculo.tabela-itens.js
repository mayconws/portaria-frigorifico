Mendes.TabelaItens = (function(){
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaVeiculosContainer = $('.js-tabela-veiculos-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);	
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item) {
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoVeiculo: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html) {
		this.tabelaVeiculosContainer.html(html);
		$('.js-tabela-veiculo-quantidade-item').on('change', onQuantidadeItemAlterado.bind(this));
		
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('click', onEventoClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('quantidade-total'));
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		var codigoVeiculo = input.data('codigo-veiculo');
		
		var resposta = $.ajax({
			url: 'item/' + codigoVeiculo,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onEventoClick(evento) {
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento) {
		var codigoVeiculo = $(evento.target).data('codigo-veiculo');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoVeiculo,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());