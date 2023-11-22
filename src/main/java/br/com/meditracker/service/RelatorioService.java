package br.com.meditracker.service;

import br.com.meditracker.dominio.RepositorioRelatorio;

public class RelatorioService {
	
	private RepositorioRelatorio repositorioRelatorio;

	public RelatorioService(RepositorioRelatorio repositorioRelatorio) {
		this.repositorioRelatorio = repositorioRelatorio;
	}
	
	public void geraPdf(String documentoPaciente) {
		repositorioRelatorio.geraPdf(documentoPaciente);
	}
	

}