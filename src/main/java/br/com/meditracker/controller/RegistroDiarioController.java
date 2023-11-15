package br.com.meditracker.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.Paciente;
import br.com.meditracker.dominio.RegistroDiario;
import br.com.meditracker.service.RegistroDiarioService;

@Path("registroDiario")
public class RegistroDiarioController {
	
	RegistroDiarioService regDiarioService = new RegistroDiarioService();
	
	@POST
    public Response adicionarNovoRegistro(RegistroDiario registroDiario
    		,@PathParam("documento_paciente") String documento_paciente) {
        
        
        try {
            
        	regDiarioService.insereRegistroDiario(registroDiario, documento_paciente);
            //pacienteService.fechaConexao();
            
            return Response.status(Response.Status.CREATED).build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

	}

}
