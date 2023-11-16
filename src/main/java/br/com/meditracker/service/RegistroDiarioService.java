package br.com.meditracker.service;

import br.com.meditracker.dominio.RegistroDiario;
import br.com.meditracker.infra.dao.RegistroDiarioDAO;

public class RegistroDiarioService {

	RegistroDiarioDAO registroDiarioDAO = new RegistroDiarioDAO();
	
	public void insereRegistroDiario(RegistroDiario registroDiario, String documento_paciente) {
		registroDiarioDAO.cadastraRegistroDiario(registroDiario, documento_paciente);
	
	
	}
		
}
