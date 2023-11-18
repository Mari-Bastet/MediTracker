package br.com.meditracker.service;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteService {
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	

	public Paciente realizaLoginPaciente(String documentoPaciente, String senhaPaciente){
		Paciente paciente = pacienteDAO.realizaLogin(documentoPaciente, senhaPaciente);
		return paciente;
	}
	
	public void insereNovoPaciente(Paciente paciente) {
		pacienteDAO.cadastraPaciente(paciente);
	}
	
}
