package br.com.meditracker.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.ImplTratamentoDiarioPaciente;
import br.com.meditracker.dominio.ImplementaTratamentoPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;

public class TratamentoPacienteService {
		
		ImplementaTratamentoPaciente implTratPaciente;  
		ImplTratamentoDiarioPaciente implTratDiaPaciente;
		
		private TratamentoDiarioPaciente tratDiaPaciente;

		

		public TratamentoPacienteService(ImplementaTratamentoPaciente implTratPaciente,
				ImplTratamentoDiarioPaciente implTratDiaPaciente) {
			this.implTratPaciente = implTratPaciente;
			this.implTratDiaPaciente = implTratDiaPaciente;
		}
		

	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		implTratPaciente.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(String documentoPaciente, LocalDate dataRegistro){
		

		boolean existeRegistro;
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		
		tratamentoDiario = implTratPaciente.listaTratamentosDoDia(documentoPaciente,dataRegistro);
		
		
		for (TratamentoPaciente trat: tratamentoDiario) {

			existeRegistro = implTratDiaPaciente.verificaExistenciaRegistroDiario(trat.getIdtratMedPaciente(), dataRegistro, documentoPaciente);
			System.out.println(existeRegistro);
			
			if(existeRegistro == false) {
				tratDiaPaciente = new TratamentoDiarioPaciente();
				tratDiaPaciente.setDataRegistroDiarioMed(dataRegistro);
				tratDiaPaciente.setIdTratMedPaciente(trat.getIdtratMedPaciente());
				tratDiaPaciente.setStaMedicamentoTomado(0);
				
				implTratDiaPaciente.insereRegistroDiarioTratamento(tratDiaPaciente);
			}
			
		}
		implTratPaciente.fecharConexao();
		
		
		return tratamentoDiario;
		
	}


}
