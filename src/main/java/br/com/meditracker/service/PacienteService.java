package br.com.meditracker.service;

import br.com.meditracker.dominio.ImplementaPaciente;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteService {
	
	
	private ImplementaPaciente implPaciente;

	public PacienteService(ImplementaPaciente implPaciente) {
		this.implPaciente = implPaciente;
	}
	
	public Paciente realizaLoginPaciente(String documentoPaciente, String senhaPaciente){
		Paciente paciente = implPaciente.realizaLogin(documentoPaciente, senhaPaciente);
		implPaciente.fecharConexao();
		return paciente;
	}
	
	public void insereNovoPaciente(Paciente paciente) {
		implPaciente.cadastraPaciente(paciente);
		implPaciente.fecharConexao();

	}

}
