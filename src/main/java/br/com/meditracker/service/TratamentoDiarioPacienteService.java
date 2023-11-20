package br.com.meditracker.service;

import java.time.LocalDate;

import br.com.meditracker.dominio.ImplTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;

public class TratamentoDiarioPacienteService {
	
	
	ImplTratamentoDiarioPaciente implTratDiaPaciente;
	
	
	public TratamentoDiarioPacienteService(ImplTratamentoDiarioPaciente implTratDiaPaciente) {
		this.implTratDiaPaciente = implTratDiaPaciente;
	}	
	
	//TratamentoDiarioPacienteDAO tratDiaPacienteDAO = new TratamentoDiarioPacienteDAO();
	
	public void AtualizaTratMedDiario(TratamentoDiarioPaciente tratDiaPaciente,LocalDate dataRegistro) {
		
		implTratDiaPaciente.atualizaRegistroDiarioMed(tratDiaPaciente, dataRegistro);
		
	}



}
