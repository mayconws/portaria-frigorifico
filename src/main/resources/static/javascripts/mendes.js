var Mendes = Mendes || {};

Mendes.MaskDate = (function() {

	function MaskDate() {
		this.inputDate = $('.js-date');
	}

	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}

	return MaskDate;

}());

Mendes.Security = (function() {

	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}

	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}

	return Security;

}());

$(function() {

	var maskDate = new Mendes.MaskDate();
	maskDate.enable();

	var security = new Mendes.Security();
	security.enable();

});

/* Mascara de telefone com 9 digitos */
var SPMaskBehavior = function(val) {
	return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
},
	spOptions = {
		onKeyPress: function(val, e, field, options) {
			field.mask(SPMaskBehavior.apply({}, arguments), options);
		}
	};

$('.telefone').mask(SPMaskBehavior, spOptions);

/* Mascara CEP */
$(document).ready(function() {
	$('.cep').mask('00000-000');
	;
});

/* Mascara CPF */
$(document).ready(function() {
	$('.cpf').mask('000.000.000-00');
	;
});

/* Macara Placa Modelo Mercosul */
$('.ms-placa').mask('AAA 0U00', {
	translation: {
		'A': {
			pattern: /[A-Za-z]/
		},
		'U': {
			pattern: /[A-Za-z0-9]/
		},
	},
	onKeyPress: function(value, e, field, options) {
		// Convert to uppercase
		e.currentTarget.value = value.toUpperCase();

		// Get only valid characters
		let val = value.replace(/[^\w]/g, '');

		// Detect plate format
		let isNumeric = !isNaN(parseFloat(val[4])) && isFinite(val[4]);
		let mask = 'AAA 0U00';
		if (val.length > 4 && isNumeric) {
			mask = 'AAA-0000';
		}
		$(field).mask(mask, options);
	}
});

/* Via CEP */

$(document).ready(function() {

	function limpa_formulário_cep() {
		// Limpa valores do formulário de cep.
		$("#rua").val("");
		$("#bairro").val("");
		$("#cidade").val("");
		$("#uf").val("");
		$("#ibge").val("");
	}

	//Quando o campo cep perde o foco.
	$(".cep").blur(function() {

		//Nova variável "cep" somente com dígitos.
		var cep = $(this).val().replace(/\D/g, '');

		//Verifica se campo cep possui valor informado.
		if (cep != "") {

			//Expressão regular para validar o CEP.
			var validacep = /^[0-9]{8}$/;

			//Valida o formato do CEP.
			if (validacep.test(cep)) {

				//Preenche os campos com "..." enquanto consulta webservice.
				$(".rua").val("...");
				$(".bairro").val("...");
				$(".cidade").val("...");
				$(".uf").val("...");
				$(".ibge").val("...");

				//Consulta o webservice viacep.com.br/
				$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {

					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$(".rua").val(dados.logradouro);
						$(".bairro").val(dados.bairro);
						$(".cidade").val(dados.localidade);
						$(".uf").val(dados.uf);
						$(".ibge").val(dados.ibge);
					} //end if.
					else {
						//CEP pesquisado não foi encontrado.
						limpa_formulário_cep();
						alert("CEP não encontrado.");
					}
				});
			} //end if.
			else {
				//cep é inválido.
				limpa_formulário_cep();
				alert("Formato de CEP inválido.");
			}
		} //end if.
		else {
			//cep sem valor, limpa formulário.
			limpa_formulário_cep();
		}
	});
});