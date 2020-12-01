var Mendes = Mendes || {};

Mendes.ModeloCadastroRapido = (function() {
	
	function ModeloCadastroRapido() {
		this.modal = $('#modalCadastroRapidoModelo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-modelo-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeModelo = $('#nomeModelo');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-modelo');
	}
	
	ModeloCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeModelo.focus();
	}
	
	function onModalClose() {
		this.inputNomeModelo.val('');
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {
		var nomeModelo = this.inputNomeModelo.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeModelo }),
			error: onErroSalvandoModelo.bind(this),
			success: onModeloSalvo.bind(this)
		});
	}
	
	function onErroSalvandoModelo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onModeloSalvo(modelo) {
		var comboModelo = $('#modelo');
		comboModelo.append('<option value=' + modelo.codigo + '>' + modelo.nome + '</option>');
		comboModelo.val(modelo.codigo);
		this.modal.modal('hide');
	}
	
	return ModeloCadastroRapido;
	
}());

$(function() {
	var modeloCadastroRapido = new Mendes.ModeloCadastroRapido();
	modeloCadastroRapido.iniciar();
});