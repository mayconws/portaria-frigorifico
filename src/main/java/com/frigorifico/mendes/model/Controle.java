package com.frigorifico.mendes.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
	private Date dataSaida = new Date();

	@Column(name = "observacao")
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private StatusVisitante status = StatusVisitante.AGUARDANDO;
	
	@ManyToOne
	@NotNull(message = "O visitante é obrigatório")
	@JoinColumn(name = "codigo_visitante")
	private Visitante visitante;
	
	@ManyToOne
	@NotNull(message = "O setor é obrigatório")
	@JoinColumn(name = "codigo_setor")
	private Setor setor;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
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
	

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
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

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	public boolean isNovo() {
		return codigo == null;
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
