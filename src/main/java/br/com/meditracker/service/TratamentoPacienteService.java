package br.com.meditracker.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.PacienteDAO;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;

public class TratamentoPacienteService {
	
		TratamentoPacienteDAO tratamentoPacienteDAO = new TratamentoPacienteDAO();
		TratamentoDiarioPacienteDAO tratDiaPacienteDAO = new TratamentoDiarioPacienteDAO();
		
		private TratamentoPaciente tratamentoPaciente;
		private TratamentoDiarioPaciente tratDiaPaciente;

	/*	public TratamentoPacienteService(TratamentoPaciente tratamentoPaciente
										,TratamentoDiarioPaciente tratDiaPaciente)
		
		{
			this.tratamentoPaciente = tratamentoPaciente;
			this.tratDiaPaciente	= tratDiaPaciente;
			
			
		}*/
	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		tratamentoPacienteDAO.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(String documentoPaciente, LocalDate dataRegistro){
		
		boolean existeRegistro;
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		
		tratamentoDiario = tratamentoPacienteDAO.listaTratamentosDoDia(documentoPaciente,dataRegistro);
		
		
		for (TratamentoPaciente trat: tratamentoDiario) {
			existeRegistro = tratDiaPacienteDAO.verificaExistenciaRegistroDiario(trat.getIdtratMedPaciente(), dataRegistro, documentoPaciente);
			
			if(existeRegistro == false) {
				tratDiaPaciente = new TratamentoDiarioPaciente();
				tratDiaPaciente.setDataRegistroDiarioMed(dataRegistro);
				tratDiaPaciente.setIdTratMedPaciente(trat.getIdtratMedPaciente());
				tratDiaPaciente.setStaMedicamentoTomado(0);
				
				tratDiaPacienteDAO.insereRegistroDiarioTratamento(tratDiaPaciente);
			}
			
		}
		
		
		return tratamentoDiario;
		
	}

}
