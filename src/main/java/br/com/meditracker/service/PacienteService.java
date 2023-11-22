package br.com.meditracker.service;

import br.com.meditracker.dominio.RepositorioPaciente;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteService {
	
	
	private RepositorioPaciente repositorioPaciente;

	public PacienteService(RepositorioPaciente repositorioPaciente) {
		this.repositorioPaciente = repositorioPaciente;
	}
	
	

	public Paciente realizaLoginPaciente(String documentoPaciente, String senhaPaciente){
		Paciente paciente = repositorioPaciente.realizaLogin(documentoPaciente, senhaPaciente);
		repositorioPaciente.fecharConexao();
		return paciente;
	}
	
	public void insereNovoPaciente(Paciente paciente) {
		repositorioPaciente.cadastraPaciente(paciente);
		repositorioPaciente.fecharConexao();

	}

}
