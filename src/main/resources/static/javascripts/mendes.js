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

$(function() {
	
	var maskDate = new Mendes.MaskDate();
	maskDate.enable();
	
});

/* Mascara de telefone com 9 digitos */
var SPMaskBehavior = function (val) {
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