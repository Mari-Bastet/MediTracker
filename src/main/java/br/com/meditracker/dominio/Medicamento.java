package br.com.meditracker.dominio;

public class Medicamento {
	
	int id_medicamento;                
	String nome_medicamento;       
	String descricao_medicamento; 
	Double dosagem_medicamento;
	
	public Medicamento(int id_medicamento, String nome_medicamento, String descricao_medicamento,
			Double dosagem_medicamento) {
		this.id_medicamento = id_medicamento;
		this.nome_medicamento = nome_medicamento;
		this.descricao_medicamento = descricao_medicamento;
		this.dosagem_medicamento = dosagem_medicamento;
	}
	
	public Medicamento(){};

	public int getId_medicamento() {
		return id_medicamento;
	}

	public void setId_medicamento(int id_medicamento) {
		this.id_medicamento = id_medicamento;
	}

	public String getNome_medicamento() {
		return nome_medicamento;
	}

	public void setNome_medicamento(String nome_medicamento) {
		this.nome_medicamento = nome_medicamento;
	}

	public String getDescricao_medicamento() {
		return descricao_medicamento;
	}

	public void setDescricao_medicamento(String descricao_medicamento) {
		this.descricao_medicamento = descricao_medicamento;
	}

	public Double getDosagem_medicamento() {
		return dosagem_medicamento;
	}

	public void setDosagem_medicamento(Double dosagem_medicamento) {
		this.dosagem_medicamento = dosagem_medicamento;
	}
}
