package br.com.meditracker.service;

import java.time.LocalDate;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;

public class TratamentoDiarioPacienteService {
	
	
	RepositorioTratamentoDiarioPaciente repositorioTratDiaPaciente;
	
	
	public TratamentoDiarioPacienteService(RepositorioTratamentoDiarioPaciente repositorioTratDiaPaciente) {
		this.repositorioTratDiaPaciente = repositorioTratDiaPaciente;
	}	
	
	
	public void AtualizaTratMedDiario(TratamentoDiarioPaciente tratDiaPaciente,LocalDate dataRegistro) {
		
		repositorioTratDiaPaciente.atualizaRegistroDiarioMed(tratDiaPaciente, dataRegistro);
		
	}



}
