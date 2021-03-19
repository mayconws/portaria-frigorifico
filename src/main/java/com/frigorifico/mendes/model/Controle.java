package com.frigorifico.mendes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "controle")
public class Controle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "data_entrada")
	private LocalDateTime dataEntrada;

	@Column(name = "data_saida")
	private LocalDateTime dataSaida;	

	@Column(name = "observacao")
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private StatusVisitante status = StatusVisitante.AGUARDANDO;
	
	@OneToMany(mappedBy = "controle", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemVisitante> itens = new ArrayList<>();
	
	@Transient
	private String uuid;
	
	@Transient
	private LocalDate dataDaSaida;

	@Transient
	private LocalTime horarioDaSaida;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusVisitante getStatus() {
		return status;
	}

	public void setStatus(StatusVisitante status) {
		this.status = status;
	}

	public List<ItemVisitante> getItens() {
		return itens;
	}

	public void setItens(List<ItemVisitante> itens) {
		this.itens = itens;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDate getDataDaSaida() {
		return dataDaSaida;
	}

	public void setDataDaSaida(LocalDate dataDaSaida) {
		this.dataDaSaida = dataDaSaida;
	}

	public LocalTime getHorarioDaSaida() {
		return horarioDaSaida;
	}

	public void setHorarioDaSaida(LocalTime horarioDaSaida) {
		this.horarioDaSaida = horarioDaSaida;
	}
	
	public void adicionarItens(List<ItemVisitante> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setControle(this));
	}
	
	public boolean isSalvarPermitido() {
		return !status.equals(StatusVisitante.CANCELADO);
	}
	
	public boolean isSalvarProibido() {
		return !isSalvarPermitido();
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataEntrada != null ? dataEntrada.toLocalDate() : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Controle other = (Controle) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	

}
