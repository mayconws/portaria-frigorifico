Mendes = Mendes || {};

Mendes.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-nome-veiculo-input');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(placaOuModelo) {
				return this.skuOuNomeInput.data('url') + '?placaOuModelo=' + placaOuModelo;
			}.bind(this),
			getValue: 'placa',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado() {
		this.emitter.trigger('item-selecionado', this.skuOuNomeInput.getSelectedItemData());
		this.skuOuNomeInput.val('');
		this.skuOuNomeInput.focus();
	}
		
	return Autocomplete
	
}());