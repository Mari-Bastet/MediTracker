package br.com.meditracker.dominio;

import java.util.Date;

public class RegistroDiario {
	
	private int id_registro_diario;
	private String descricao_registro;
	private int id_paciente;
	private int humor;
	private Date data_registro;
	
	public RegistroDiario(int id_registro_diario, String descricao_registro, int id_paciente, int humor,
			Date data_registro) {
		
		this.id_registro_diario = id_registro_diario;
		this.descricao_registro = descricao_registro;
		this.id_paciente = id_paciente;
		this.humor = humor;
		this.data_registro = data_registro;
	}
	
	public int getId_registro_diario() {
		return id_registro_diario;
	}

	public void setId_registro_diario(int id_registro_diario) {
		this.id_registro_diario = id_registro_diario;
	}

	public String getDescricao_registro() {
		return descricao_registro;
	}

	public void setDescricao_registro(String descricao_registro) {
		this.descricao_registro = descricao_registro;
	}

	public int getId_paciente() {
		return id_paciente;
	}

	public void setId_paciente(int id_paciente) {
		this.id_paciente = id_paciente;
	}

	public int getHumor() {
		return humor;
	}

	public void setHumor(int humor) {
		this.humor = humor;
	}

	public Date getData_registro() {
		return data_registro;
	}

	public void setData_registro(Date data_registro) {
		this.data_registro = data_registro;
	}


}
