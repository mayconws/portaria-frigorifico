var Mendes = Mendes || {};

Mendes.UploadFoto = (function() {

	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		this.novaFoto = $('input[name=novaFoto]');
		this.inputUrlFoto = $('input[name=urlFoto]');
		
		this.htmlFotoVeiculoTemplate = $('#foto-veiculo').html();
		this.template = Handlebars.compile(this.htmlFotoVeiculoTemplate);

		this.containerFotoVeiculo = $('.js-container-foto-veiculo');

		this.uploadDrop = $('#upload-drop');
	}

	UploadFoto.prototype.iniciar = function() {
		var settings = {
			type : 'json',
			filelimit : 1,
			allow : '*.(jpg|jpeg|png)',
			action : this.containerFotoVeiculo.data('url-fotos'),
			complete : onUploadCompleto.bind(this),
			beforeSend: adicionarCsrfToken
		}

		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if (this.inputNomeFoto.val()) {
			renderizarFoto.call(this, { 
				nome:  this.inputNomeFoto.val(), 
				contentType: this.inputContentType.val(), 
				url: this.inputUrlFoto.val()});
		}
	}

	function onUploadCompleto(resposta) {
		this.novaFoto.val('true');
		this.inputUrlFoto.val(resposta.url);
		renderizarFoto.call(this, resposta);
	}
	
	function renderizarFoto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		
		var htmlFotoVeiculo = this.template({url: resposta.url});
		this.containerFotoVeiculo.append(htmlFotoVeiculo);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	
	}

	function onRemoverFoto() {
		$('.js-foto-veiculo').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
		this.novaFoto.val('false');
	}
	
	function adicionarCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}

	return UploadFoto;

})();

$(function() {
	var uploadFoto = new Mendes.UploadFoto();
	uploadFoto.iniciar();
});