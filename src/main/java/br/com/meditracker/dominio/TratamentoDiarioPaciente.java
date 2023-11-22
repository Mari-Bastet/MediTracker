package br.com.meditracker.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TratamentoDiarioPaciente {
	
	
	@JsonProperty
	private int idRegistroDiarioMed;   
	
	@JsonProperty
	private LocalDate dataRegistroDiarioMed;   
	
	@JsonProperty
	private int idTratMedPaciente;     
	
	@JsonProperty
	private int staMedicamentoTomado;
	
	public TratamentoDiarioPaciente(int idRegistroDiarioMed, LocalDate dataRegistroDiarioMed, int idTratMedPaciente,
			int staMedicamentoTomado) {
		this.idRegistroDiarioMed = idRegistroDiarioMed;
		this.dataRegistroDiarioMed = dataRegistroDiarioMed;
		this.idTratMedPaciente = idTratMedPaciente;
		this.staMedicamentoTomado = staMedicamentoTomado;
	}
	
	public TratamentoDiarioPaciente() {};
	

	public int getIdRegistroDiarioMed() {
		return idRegistroDiarioMed;
	}

	public void setIdRegistroDiarioMed(int idRegistroDiarioMed) {
		this.idRegistroDiarioMed = idRegistroDiarioMed;
	}

	public LocalDate getDataRegistroDiarioMed() {
		return dataRegistroDiarioMed;
	}

	public void setDataRegistroDiarioMed(LocalDate dataRegistroDiarioMed) {
		this.dataRegistroDiarioMed = dataRegistroDiarioMed;
	}

	public int getIdTratMedPaciente() {
		return idTratMedPaciente;
	}

	public void setIdTratMedPaciente(int idTratMedPaciente) {
		this.idTratMedPaciente = idTratMedPaciente;
	}

	public void setStaMedicamentoTomado(int staMedicamentoTomado) {
		this.staMedicamentoTomado = staMedicamentoTomado;
	}


}
