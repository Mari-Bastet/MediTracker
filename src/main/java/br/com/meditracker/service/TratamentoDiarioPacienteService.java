package br.com.meditracker.service;

import java.time.LocalDate;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;

public class TratamentoDiarioPacienteService {
	
	
	RepositorioTratamentoDiarioPaciente repositorioTratamentoDiarioPaciente;
	
	
	public TratamentoDiarioPacienteService(RepositorioTratamentoDiarioPaciente implTratDiaPaciente) {
		this.repositorioTratamentoDiarioPaciente = implTratDiaPaciente;
	}	
	
	
	public void AtualizaTratMedDiario(TratamentoDiarioPaciente tratDiaPaciente,LocalDate dataRegistro) {
		
		repositorioTratamentoDiarioPaciente.atualizaRegistroDiarioMed(tratDiaPaciente, dataRegistro);
		repositorioTratamentoDiarioPaciente.fecharConexao();
	}



}
