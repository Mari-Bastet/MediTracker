package br.com.health.infra.dao.test;

import org.junit.Test;

import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;
import br.com.meditracker.service.TratamentoPacienteService;

public class TratamentoPacienteDAOTest {
	
	@Test
	public void testaInsercaoPacienteDAO() {
	 TratamentoPacienteDAO tratPaciDAO = new TratamentoPacienteDAO();
	 TratamentoPaciente tratPaci = new TratamentoPaciente(50,20.2,null,1,"Diazepam",1,"Cancer",1,1,null);
	 
	 tratPaciDAO.insereTratamentoPaciente(tratPaci, "78901234508");
	}
	
	@Test
	public void testaInsercaoPacienteService() {
	 TratamentoPacienteService tratPaciServic = new TratamentoPacienteService();
	 TratamentoPaciente tratPaci = new TratamentoPaciente(50,20.2,null,1,"Diazepam",1,"Cancer",1,1,null);
	 
	 tratPaciServic.insereNovoTratamentoPaciente(tratPaci, "78901234508");
	}

}
