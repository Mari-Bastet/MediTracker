package br.com.meditracker.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.RepositorioTratamentoPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;


public class TratamentoPacienteService {
		
		RepositorioTratamentoPaciente repositorioTratamentoPaciente;  
		RepositorioTratamentoDiarioPaciente repositorioTratamentoDiarioPaciente;
		
		private TratamentoDiarioPaciente tratDiaPaciente;

		

		public TratamentoPacienteService(RepositorioTratamentoPaciente repositorioTratamentoPaciente,
				RepositorioTratamentoDiarioPaciente repositorioTratamentoDiarioPaciente) {
			this.repositorioTratamentoPaciente = repositorioTratamentoPaciente;
			this.repositorioTratamentoDiarioPaciente = repositorioTratamentoDiarioPaciente;
		}
		

	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		repositorioTratamentoPaciente.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(String documentoPaciente, LocalDate dataRegistro){
		

		boolean existeRegistro;
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		
		tratamentoDiario = repositorioTratamentoPaciente.listaTratamentosDoDia(documentoPaciente,dataRegistro);
		
		
		for (TratamentoPaciente trat: tratamentoDiario) {

			existeRegistro = repositorioTratamentoDiarioPaciente.verificaExistenciaRegistroDiario(trat.getIdtratMedPaciente(), dataRegistro, documentoPaciente);
			
			if(existeRegistro == false) {
				tratDiaPaciente = new TratamentoDiarioPaciente();
				tratDiaPaciente.setDataRegistroDiarioMed(dataRegistro);
				tratDiaPaciente.setIdTratMedPaciente(trat.getIdtratMedPaciente());
				tratDiaPaciente.setStaMedicamentoTomado(0);
				
				repositorioTratamentoDiarioPaciente.insereRegistroDiarioTratamento(tratDiaPaciente);
			}
			
		}
		repositorioTratamentoDiarioPaciente.fecharConexao();
		
		
		return tratamentoDiario;
		
	}


}
