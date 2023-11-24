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

import br.com.meditracker.dominio.RepositorioPaciente;
import br.com.meditracker.dominio.RepositorioPerguntas;
import br.com.editracker.infra.ia.IntegracaoIA;
import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.infra.dao.PacienteDAO;
import br.com.meditracker.service.PacienteService;


@Path("paciente")
public class PacienteController {
	
	private RepositorioPaciente pacienteDAO;
	private PacienteService pacienteService;
	//private RepositorioPerguntas integracaoIA;
	
	public PacienteController() {
		pacienteDAO = new PacienteDAO();
		//integracaoIA = new IntegracaoIA();
		
		pacienteService  = new PacienteService(pacienteDAO);
	}
	
    @GET
    @Path("/{DOCUMENTO_PACIENTE}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaPacienteLogin(@PathParam("DOCUMENTO_PACIENTE") String documentoPaciente
    									 ,@QueryParam("SENHA_PACIENTE") String senhaPaciente) {
        Response.Status status = null;
        
        
        
        try {
        Paciente paciente = pacienteService.realizaLoginPaciente(documentoPaciente, senhaPaciente);

        if (paciente == null) {
            status = Response.Status.NOT_FOUND;
            return Response.status(status).entity("Login incorreto, favor verifique os dados informados").build();
        } else {
            status = Response.Status.OK;
            return Response.status(status).entity(paciente).build();
        }
        
        
    	}catch(RuntimeException e) {
    		e.printStackTrace();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    }
    
    @POST
    public Response adicionarPaciente(Paciente paciente) {
        
        try {
            System.out.println(paciente.getDocumentoPaciente());
            pacienteService.insereNovoPaciente(paciente);
            //pacienteService.fechaConexao();
            
            return Response.status(Response.Status.CREATED).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }
    
    @GET
    @Path("/perguntas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retornaResposta(@QueryParam("PERGUNTA") String pergunta) {
        Response.Status status = null;
        
        
        
        try {
        String pacientePergunta = pacienteService.respondePerguntaAssistente(pergunta);

        if (pacientePergunta == null) {
            status = Response.Status.NOT_FOUND;
            return Response.status(status).entity("Não foi possível responder sua pergunta").build();
        } else {
            status = Response.Status.OK;
            return Response.status(status).entity(pacientePergunta).build();
        }
        
        
    	}catch(RuntimeException e) {
    		e.printStackTrace();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    	}
    }
    
    

}
