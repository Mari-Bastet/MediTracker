package br.com.meditracker.service;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteService {
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	

	public Paciente realizaLoginPaciente(String documento_paciente, String senha_paciente){
		Paciente paciente = pacienteDAO.realizaLogin(documento_paciente, senha_paciente);
		return paciente;
	}
	
	public void insereNovoPaciente(Paciente paciente) {
		pacienteDAO.cadastraPaciente(paciente);
	}
	
}
