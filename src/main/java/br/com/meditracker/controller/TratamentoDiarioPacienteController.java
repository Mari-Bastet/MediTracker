package br.com.meditracker.controller;

import java.time.LocalDate;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.TratamentoDiarioPaciente;
import br.com.meditracker.infra.dao.TratamentoDiarioPacienteDAO;

@Path("tratDiarioPaciente")
public class TratamentoDiarioPacienteController {
	
	//TratamentoDiarioPacienteController tratDiaPacDAO = new TratamentoDiarioPacienteController();
	
	TratamentoDiarioPacienteDAO tratDiaPacDAO = new TratamentoDiarioPacienteDAO();

	
	@PUT
	public Response atualizaRegDiario(TratamentoDiarioPaciente tratDiaPaciente
									,@QueryParam("DATA_REGISTRO") String dataRegistro) {
		
        try {
        	
	        LocalDate data = LocalDate.parse(dataRegistro);
	        
	        //tratDiaPacDAO.atualizaRegDiario(tratDiaPaciente, data);
	        
	        tratDiaPacDAO.atualizaRegistroDiarioMed(tratDiaPaciente, data);
        	
            //pacienteService.fechaConexao();
            
            return Response.status(Response.Status.OK).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
		
	}

}
