package br.com.meditracker.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.dominio.TratamentoPaciente;
import br.com.meditracker.infra.dao.TratamentoPacienteDAO;
import br.com.meditracker.service.TratamentoPacienteService;

@Path("tratamentoPaciente")
public class TratamentoPacienteController {
	
	//TratamentoPacienteService tratamentoDAO = new TratamentoPacienteService();
	TratamentoPacienteService tratDAO = new TratamentoPacienteService();
	
	@GET
	@Path("/{DOCUMENTO_PACIENTE}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response retornaTratamentoDiario(@PathParam("DOCUMENTO_PACIENTE") String documentoPaciente
										   ,@QueryParam("DATA_REGISTRO") String dataRegistro) 
	{
		
		
		try {
	        LocalDate data = LocalDate.parse(dataRegistro);
			ArrayList<TratamentoPaciente> medicamentos = tratDAO.listaTratamentoDiarioPaciente(documentoPaciente, data);
			Status status = null;
			
			if(medicamentos.isEmpty()) {
				
				status = Response.Status.NOT_FOUND;
				return Response.status(status).entity("Não há medicamentos para tomar hoje").build();

			}
			else {
				
				status = Response.Status.OK;
				return Response.status(status).entity(medicamentos).build();

			}
						
		}catch(RuntimeException e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
				
		}
		
		
	}
	

	@POST
	@Path("/{DOCUMENTO_PACIENTE}")
    public Response adicionaTratamento(TratamentoPaciente tratamentoPaciente
    									,@PathParam("DOCUMENTO_PACIENTE")String documentoPaciente) {
    	tratamentoPaciente.getIdtratMedPaciente();

        
        try {
        	
        	tratDAO.insereNovoTratamentoPaciente(tratamentoPaciente,documentoPaciente);
        	
            //pacienteService.fechaConexao();
            
            return Response.status(Response.Status.CREATED).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

	}
	
	
	

}
