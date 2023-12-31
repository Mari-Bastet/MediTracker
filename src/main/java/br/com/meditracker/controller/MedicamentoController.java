package br.com.meditracker.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.RepositorioMedicamento;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;
import br.com.meditracker.service.MedicamentoService;

@Path("medicamento")
public class MedicamentoController {

	private RepositorioMedicamento medicamentoDAO; 
	private MedicamentoService medicamentoService;

	public MedicamentoController() {
		 medicamentoDAO = new 	MedicamentoDAO();
		 medicamentoService = new MedicamentoService(medicamentoDAO);

	}
	
	@GET
	@Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
	public Response listaMedicamentos() {
		
		System.out.println(medicamentoDAO);
		Response.Status status = null;
		
		try {
			ArrayList<Medicamento> medicamentos = medicamentoService.retornaMedicamentos();
			if(medicamentos.isEmpty()) {
				
				status = Response.Status.NOT_FOUND;
				return Response.status(status).entity("Não há remédios para listar.").build();

			}
			else {
				
				status = Response.Status.OK;
				return Response.status(status).entity(medicamentos).build();

			}
						
		}catch(RuntimeException e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
				
		}

	}
}
