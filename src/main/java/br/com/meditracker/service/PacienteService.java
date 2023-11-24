package br.com.meditracker.service;

import br.com.meditracker.dominio.RepositorioPaciente;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteService {
	
	
	private RepositorioPaciente repositorioPaciente;
	//private RepositorioPerguntas repostorioPerguntas;

	public PacienteService(RepositorioPaciente repositorioPaciente) {
		this.repositorioPaciente = repositorioPaciente;
		//this.repostorioPerguntas = repostorioPerguntas;
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
	
	public String respondePerguntaAssistente(String pergunta) {
		Paciente paciente = new Paciente();
		String resposta = paciente.realizaPergunta(pergunta);
		return resposta;
		
	}

}
