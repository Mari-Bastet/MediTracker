package br.com.meditracker.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.meditracker.infra.dao.RelatorioTratamentoPacienteDAO;

@Path("downloads")
public class RelatorioPacienteController {
	
	
	RelatorioTratamentoPacienteDAO relatorio = new RelatorioTratamentoPacienteDAO() ;

	@GET
	@Path("/{DOCUMENTO_PACIENTE}")
	public Response downloadRelatorio(@PathParam("DOCUMENTO_PACIENTE") String documentoPaciente ) {
		
		
        try {
        	relatorio.geraPdf(documentoPaciente);
            
            return Response.status(Response.Status.CREATED).entity("Relatório salvo na área de trabalho!").build();
            
        }catch(RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }		
		
		
	}
	
	
}
