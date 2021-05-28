package com.frigorifico.mendes.model;

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

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "data_chegada")
	private LocalDateTime dataChegada;

	@Column(name = "data_saida")
	private Date dataSaida = new Date();

	@Column(name = "observacao_chegada")
	private String observacaoChegada;

	@Column(name = "observacao_saida")
	private String observacaoSaida;

	@ManyToOne
	@JoinColumn(name = "codigo_motorista")
	private Motorista motorista;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private StatusVeiculo status = StatusVeiculo.AGUARDANDO;

	@Enumerated(EnumType.STRING)
	private SituacaoVeiculo situacao;

	@ManyToOne
	@JoinColumn(name = "codigo_veiculo")
	private Veiculo veiculo;

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

	public LocalDateTime getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(LocalDateTime dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getObservacaoChegada() {
		return observacaoChegada;
	}

	public void setObservacaoChegada(String observacaoChegada) {
		this.observacaoChegada = observacaoChegada;
	}

	public String getObservacaoSaida() {
		return observacaoSaida;
	}

	public void setObservacaoSaida(String observacaoSaida) {
		this.observacaoSaida = observacaoSaida;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusVeiculo getStatus() {
		return status;
	}

	public void setStatus(StatusVeiculo status) {
		this.status = status;
	}

	public SituacaoVeiculo getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoVeiculo situacao) {
		this.situacao = situacao;
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public boolean isNova() {
		return codigo == null;
	}
	
	public boolean isSalvarPermitido() {
		return !status.equals(StatusVeiculo.CANCELADO);
	}
	
	public boolean isSalvarProibido() {
		return !isSalvarPermitido();
	}
	
	public Long getDiasCriacao() {
		LocalDate inicio = dataChegada != null ? dataChegada.toLocalDate() : LocalDate.now();
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
		Movimentacao other = (Movimentacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
