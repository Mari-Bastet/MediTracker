package br.com.meditracker.service;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.RepositorioTratamentoPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;

public class TratamentoPacienteService {
		
		RepositorioTratamentoPaciente repositorioTratPaciente;  
		RepositorioTratamentoDiarioPaciente repositorioTratDiaPaciente;
		
		private TratamentoDiarioPaciente tratDiaPaciente;

		

		public TratamentoPacienteService(RepositorioTratamentoPaciente repositorioTratPaciente,
				RepositorioTratamentoDiarioPaciente repositorioTratDiaPaciente) {
			this.repositorioTratPaciente = repositorioTratPaciente;
			this.repositorioTratDiaPaciente = repositorioTratDiaPaciente;
		}
		

	public void insereNovoTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente) {
		
		repositorioTratPaciente.insereTratamentoPaciente(tratamentoPaciente, documentoPaciente);
		
	}
	
	public ArrayList<TratamentoPaciente> listaTratamentoDiarioPaciente(String documentoPaciente, LocalDate dataRegistro){
		

		boolean existeRegistro;
		ArrayList<TratamentoPaciente> tratamentoDiario = new ArrayList<>();
		
		
		tratamentoDiario = repositorioTratPaciente.listaTratamentosDoDia(documentoPaciente,dataRegistro);
		
		
		for (TratamentoPaciente trat: tratamentoDiario) {

			existeRegistro = repositorioTratDiaPaciente.verificaExistenciaRegistroDiario(trat.getIdtratMedPaciente(), dataRegistro, documentoPaciente);
			System.out.println(existeRegistro);
			
			if(existeRegistro == false) {
				tratDiaPaciente = new TratamentoDiarioPaciente();
				tratDiaPaciente.setDataRegistroDiarioMed(dataRegistro);
				tratDiaPaciente.setIdTratMedPaciente(trat.getIdtratMedPaciente());
				tratDiaPaciente.setStaMedicamentoTomado(0);
				
				repositorioTratDiaPaciente.insereRegistroDiarioTratamento(tratDiaPaciente);
			}
			
		}
		repositorioTratPaciente.fecharConexao();
		
		
		return tratamentoDiario;
		
	}


}
