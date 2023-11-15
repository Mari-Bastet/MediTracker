package br.com.meditracker.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.service.PacienteService;


@Path("paciente")
public class PacienteController {
	
	private PacienteService pacienteService = new PacienteService();
	
    @GET
    @Path("/{DOCUMENTO_PACIENTE}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaPacienteLogin(@PathParam("DOCUMENTO_PACIENTE") String DOCUMENTO_PACIENTE
    									 ,@QueryParam("SENHA_PACIENTE") String SENHA_PACIENTE) {
        Response.Status status = null;
        
        
        try {
        Paciente paciente = pacienteService.realizaLoginPaciente(DOCUMENTO_PACIENTE, SENHA_PACIENTE);

        if (paciente == null) {
            status = Response.Status.NOT_FOUND;
            return Response.status(status).entity("Paciente não encontrado, verifique os dados informados").build();
        } else {
            status = Response.Status.OK;
            return Response.status(status).entity(paciente).build();
        }
        
        
    	}catch(RuntimeException e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    }
    
    @POST
    public Response adicionarPaciente(Paciente paciente) {
        
        
        try {
            
            pacienteService.insereNovoPaciente(paciente);
            //pacienteService.fechaConexao();
            
            return Response.status(Response.Status.CREATED).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

}

}
