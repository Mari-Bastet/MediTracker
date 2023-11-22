package br.com.meditracker.service;

import java.time.LocalDate;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;

public class TratamentoDiarioPacienteService {
	
	
	RepositorioTratamentoDiarioPaciente implTratDiaPaciente;
	
	
	public TratamentoDiarioPacienteService(RepositorioTratamentoDiarioPaciente implTratDiaPaciente) {
		this.implTratDiaPaciente = implTratDiaPaciente;
	}	
	
	
	public void AtualizaTratMedDiario(TratamentoDiarioPaciente tratDiaPaciente,LocalDate dataRegistro) {
		
		implTratDiaPaciente.atualizaRegistroDiarioMed(tratDiaPaciente, dataRegistro);
		
	}



}
