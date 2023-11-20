package br.com.meditracker.service;

import java.time.LocalDate;

import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;

public class TratamentoDiarioPacienteService {
	
	TratamentoDiarioPacienteDAO tratDiaPacienteDAO = new TratamentoDiarioPacienteDAO();
	
	public void atualizaTratMedDiario(TratamentoDiarioPaciente tratDiaPaciente,LocalDate dataRegistro) {
		
		tratDiaPacienteDAO.atualizaRegistroDiarioMed(tratDiaPaciente, dataRegistro);
		
	}	

}
