package br.com.meditracker.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.service.DoencaService;

public class DoencaController {
	
	DoencaService doencaService = new DoencaService();
	
	@GET
	@Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listaMedicamentos() {
		
		Response.Status status = null;
		
		try {
			ArrayList<Doenca> doencas = doencaService.listaDoencas();
			if(doencas.isEmpty()) {
				
				status = Response.Status.NOT_FOUND;
				return Response.status(status).entity("Não há doenças para listar.").build();

			}
			else {
				
				status = Response.Status.OK;
				return Response.status(status).entity(doencas).build();

			}
						
		}catch(RuntimeException e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
				
		}

	}

}
