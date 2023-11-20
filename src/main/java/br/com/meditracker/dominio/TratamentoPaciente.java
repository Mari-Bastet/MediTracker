package br.com.meditracker.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TratamentoPaciente {
	
	@JsonProperty
	private LocalDate dataInicioTratamento;
	
	@JsonProperty
	private int idMedicamento;
	
	@JsonProperty
	private String nomeMedicamento;
	
	@JsonProperty
	private int idDoenca;
	
	@JsonProperty
	private String nomeDoenca;

	@JsonProperty
	private int idPaciente;
	
	@JsonProperty
	private int tratamentoAtivo;
	
	@JsonProperty
	private LocalDate dataTerminoTratamento;
	
	@JsonProperty
	private Double quantidadeMedicamento;
	
	@JsonProperty
	private int idtratMedPaciente;
	
	
	@JsonProperty
	private int staMedicamentoTomado;
	
	
	public TratamentoPaciente(int idtratMedPaciente, Double quantidadeMedicamento,
			LocalDate dataInicioTratamento, int idMedicamento, String nomeMedicamento, int idDoenca, String nomeDoenca,
			int idPaciente, int tratamentoAtivo, LocalDate dataTerminoTratamento, int staMedicamentoTomado) {

		this.idtratMedPaciente = idtratMedPaciente;
		this.quantidadeMedicamento = quantidadeMedicamento;
		this.dataInicioTratamento = dataInicioTratamento;
		this.idMedicamento = idMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.idDoenca = idDoenca;
		this.nomeDoenca = nomeDoenca;
		this.idPaciente = idPaciente;
		this.tratamentoAtivo = tratamentoAtivo;
		this.dataTerminoTratamento = dataTerminoTratamento;
		this.staMedicamentoTomado = staMedicamentoTomado;
	}
	
	public TratamentoPaciente() {}

	public int getIdtratMedPaciente() {
		return idtratMedPaciente;
	}
	public void setIdtratMedPaciente(int idtratMedPaciente) {
		this.idtratMedPaciente = idtratMedPaciente;
	}
	public Double getQuantidadeMedicamento() {
		return quantidadeMedicamento;
	}
	public void setQuantidadeMedicamento(Double quantidadeMedicamento) {
		this.quantidadeMedicamento = quantidadeMedicamento;
	}
	public LocalDate getDataInicioTratamento() {
		return dataInicioTratamento;
	}
	public void setDataInicioTratamento(LocalDate dataInicioTratamento) {
		this.dataInicioTratamento = dataInicioTratamento;
	}
	public int getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getNomeMedicamento() {
		return nomeMedicamento;
	}
	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}
	public int getIdDoenca() {
		return idDoenca;
	}
	public void setIdDoenca(int idDoenca) {
		this.idDoenca = idDoenca;
	}
	public String getNomeDoenca() {
		return nomeDoenca;
	}
	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getTratamentoAtivo() {
		return tratamentoAtivo;
	}
	public void setTratamentoAtivo(int tratamentoAtivo) {
		this.tratamentoAtivo = tratamentoAtivo;
	}
	public LocalDate getDataTerminoTratamento() {
		return dataTerminoTratamento;
	}
	public void setDataTerminoTratamento(LocalDate dataTerminoTratamento) {
		this.dataTerminoTratamento = dataTerminoTratamento;
	}

	public int getStaMedicamentoTomado() {
		return staMedicamentoTomado;
	}

	public void setStaMedicamentoTomado(int staMedicamentoTomado) {
		this.staMedicamentoTomado = staMedicamentoTomado;
	}
}
