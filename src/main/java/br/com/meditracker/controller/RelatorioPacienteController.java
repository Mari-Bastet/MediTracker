package br.com.meditracker.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.meditracker.dominio.RepositorioRelatorio;
import br.com.meditracker.infra.dao.RelatorioTratamentoPacienteDAO;
import br.com.meditracker.service.RelatorioService;

@Path("downloads")
public class RelatorioPacienteController {
	
	
	private RepositorioRelatorio relatorioDAO;
	private RelatorioService relatorioService;
	
	public RelatorioPacienteController() {
		relatorioDAO = new RelatorioTratamentoPacienteDAO();
		
		relatorioService = new RelatorioService(relatorioDAO);
		
	}

	@GET
	@Path("/relatorioTratamento/{DOCUMENTO_PACIENTE}")
	public Response downloadRelatorio(@PathParam("DOCUMENTO_PACIENTE") String documentoPaciente ) {
		
		
        try {
        	relatorioService.geraPdf(documentoPaciente);
            
            return Response.status(Response.Status.OK).entity("Relatório salvo na área de trabalho!").build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }		
		
		
	}
	
	
}
