package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.PacienteDAO;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;

public class TratamentoPacienteService {
	
		TratamentoPacienteDAO tratamentoPacienteDAO = new TratamentoPacienteDAO();
		
	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		tratamentoPacienteDAO.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(){
		
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		tratamentoDiario = tratamentoPacienteDAO.listaTratamentosDoDia();
		
		return tratamentoDiario;
		
	}

}
