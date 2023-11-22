package br.com.meditracker.service;

import br.com.meditracker.dominio.RepositorioPacientes;
import br.com.meditracker.dominio.RepositorioRelatorio;
import br.com.meditracker.dominio.Paciente;

public class PacienteService {
	
	
	private RepositorioPacientes repositorioPacientes;

	public PacienteService(RepositorioPacientes implPaciente,RepositorioRelatorio repositorioRelatorio) {
		this.repositorioPacientes = implPaciente;
	}
	
	public Paciente realizaLoginPaciente(String documentoPaciente, String senhaPaciente){
		Paciente paciente = repositorioPacientes.realizaLogin(documentoPaciente, senhaPaciente);
		repositorioPacientes.fecharConexao();
		return paciente;
	}
	
	public void insereNovoPaciente(Paciente paciente) {
		repositorioPacientes.cadastraPaciente(paciente);
		repositorioPacientes.fecharConexao();

	}
	

}
