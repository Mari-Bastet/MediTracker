package br.com.meditracker.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;

public class TratamentoPacienteService {
	
		TratamentoPacienteDAO tratamentoPacienteDAO = new TratamentoPacienteDAO();
		TratamentoDiarioPacienteDAO tratDiaPacienteDAO = new TratamentoDiarioPacienteDAO();
		//DoencaService doencaDAO = new DoencaService();
		
		private TratamentoDiarioPaciente tratDiaPaciente;

	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		tratamentoPacienteDAO.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(String documentoPaciente, LocalDate dataRegistro){
		
		boolean existeRegistro;
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		
		tratamentoDiario = tratamentoPacienteDAO.listaTratamentosDoDia(documentoPaciente,dataRegistro);
		
		
		for (TratamentoPaciente trat: tratamentoDiario) {

			existeRegistro = tratDiaPacienteDAO.verificaExistenciaRegistroDiario(trat.getIdtratMedPaciente(), dataRegistro, documentoPaciente);
			System.out.println(existeRegistro);
			
			if(existeRegistro == false) {
				tratDiaPaciente = new TratamentoDiarioPaciente();
				tratDiaPaciente.setDataRegistroDiarioMed(dataRegistro);
				tratDiaPaciente.setIdTratMedPaciente(trat.getIdtratMedPaciente());
				tratDiaPaciente.setStaMedicamentoTomado(0);
				
				tratDiaPacienteDAO.insereRegistroDiarioTratamento(tratDiaPaciente);
			}
			
		}
		tratamentoPacienteDAO.fecharConexao();
		
		
		return tratamentoDiario;
		
	}

}
