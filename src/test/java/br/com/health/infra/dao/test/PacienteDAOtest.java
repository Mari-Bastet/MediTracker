package br.com.health.infra.dao.test;

import java.text.SimpleDateFormat;

import org.junit.Test;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;

public class PacienteDAOtest {
	
	/*@Test
	public void testaSelectPaciente() {
		PacienteDAO pacienteDAO = new PacienteDAO();
		
		Paciente paciente = pacienteDAO.realizaLogin("45633416813", "nelson123");
		System.out.println(paciente.getDocumento_paciente());
		System.out.println("alo");
		
		
		
	}*/
	
	@Test
	public void testaInserePaciente() {
		
		PacienteDAO pacienteDAO = new PacienteDAO();
		
		
		Paciente paciente = new Paciente(1,"arlindo",null,"44444","1234","signteste");
		
		pacienteDAO.cadastraPaciente(paciente);
		
		
	}

}
