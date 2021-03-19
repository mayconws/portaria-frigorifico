package com.frigorifico.mendes.dto;

import org.springframework.util.StringUtils;

import com.frigorifico.mendes.model.Modelo;
import com.frigorifico.mendes.model.Transportadora;

public class VeiculoDTO {
	
	private Long codigo;
	private String placa;
	private String modelo;
	private String transportadora;
	private String foto;
	private String urlThumbnailFoto;
	
	public VeiculoDTO(Long codigo, String placa, Modelo modelo, Transportadora transportadora, String foto) {
		super();
		this.codigo = codigo;
		this.placa = placa;
		this.modelo = modelo.getNome();
		this.transportadora = transportadora.getNome();
		this.foto = StringUtils.isEmpty(foto) ? "veiculo-mock.png" : foto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}	

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	

	public String getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(String transportadora) {
		this.transportadora = transportadora;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUrlThumbnailFoto() {
		return urlThumbnailFoto;
	}

	public void setUrlThumbnailFoto(String urlThumbnailFoto) {
		this.urlThumbnailFoto = urlThumbnailFoto;
	}	
	
}
