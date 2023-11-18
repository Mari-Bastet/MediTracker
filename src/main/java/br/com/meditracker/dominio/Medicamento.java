package br.com.meditracker.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicamento {
	
	@JsonProperty
	private int     idMedicamento;             

	@JsonProperty
	private String nomeMedicamento;       

	@JsonProperty
	private String descricaoMedicamento; 

	@JsonProperty
	private Double dosagemMedicamento;
	
	@JsonProperty
	private String tipoDosagemMedicamento;
	
	
	public Medicamento(int idMedicamento, String nomeMedicamento, String descricaoMedicamento,
			Double dosagemMedicamento) {
		this.idMedicamento = idMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.descricaoMedicamento = descricaoMedicamento;
		this.dosagemMedicamento = dosagemMedicamento;
	}
	
	public Medicamento(){};

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

	public String getDescricaoMedicamento() {
		return descricaoMedicamento;
	}

	public void setDescricaoMedicamento(String descricaoMedicamento) {
		this.descricaoMedicamento = descricaoMedicamento;
	}

	public Double getDosagemMedicamento() {
		return dosagemMedicamento;
	}

	public void setDosagemMedicamento(Double dosagemMedicamento) {
		this.dosagemMedicamento = dosagemMedicamento;
	}
}
