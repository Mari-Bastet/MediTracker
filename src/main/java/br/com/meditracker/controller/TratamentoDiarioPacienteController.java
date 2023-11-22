package br.com.meditracker.controller;

import java.time.LocalDate;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.RepositorioTratamentoDiarioPaciente;
import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;
import br.com.meditracker.service.TratamentoDiarioPacienteService;

@Path("tratDiarioPaciente")
public class TratamentoDiarioPacienteController {
	
	//TratamentoDiarioPacienteController tratDiaPacDAO = new TratamentoDiarioPacienteController();
	
	RepositorioTratamentoDiarioPaciente tratDiaPacienteDAO;
	TratamentoDiarioPacienteService tratDiaPacienteService;
	
	public TratamentoDiarioPacienteController() {
		
		tratDiaPacienteDAO = new TratamentoDiarioPacienteDAO();	
		tratDiaPacienteService = new TratamentoDiarioPacienteService(tratDiaPacienteDAO);
		
	}
	
	TratamentoDiarioPacienteDAO tratDiaPacDAO = new TratamentoDiarioPacienteDAO();

	
	@PUT
	public Response atualizaRegDiario(TratamentoDiarioPaciente tratDiaPaciente
									,@QueryParam("DATA_REGISTRO") String dataRegistro) {
		
        try {
        	
	        LocalDate data = LocalDate.parse(dataRegistro);
	        	        
	        tratDiaPacienteService.AtualizaTratMedDiario(tratDiaPaciente, data);
        	
            
            return Response.status(Response.Status.OK).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
		
	}

}
