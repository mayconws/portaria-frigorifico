package com.frigorifico.mendes.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "O cep é obrigatório")
	private String cep;

	@NotBlank(message = "O endereço é obrigatório")
	private String logradouro;

	@NotBlank(message = "O bairro é obrigatório")
	private String bairro;

	@NotBlank(message = "O complemento é obrigatório")
	private String complemento;

	@NotBlank(message = "O número é obrigatório")
	private String numero;
	
	@NotNull(message = "A cidade é obrigatória")
	@ManyToOne
	@JoinColumn(name = "codigo_cidade")
	private Cidade cidade;

	@Transient
	private Estado estado;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNomeCidadeSiglaEstado() {
		if (this.cidade != null) {
			return this.cidade.getNome() + " - " + this.cidade.getEstado().getSigla();
		}
		
		return null;
	}		

}
