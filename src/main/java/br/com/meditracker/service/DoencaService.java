package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.dominio.RepositorioDoencas;
import br.com.meditracker.infra.dao.DoencaDAO;

public class DoencaService {
	
	private RepositorioDoencas repositorioDoencas;
	
	public DoencaService(RepositorioDoencas implDoenca) {
		this.repositorioDoencas = implDoenca;
	}	

	public ArrayList<Doenca> listaDoencas() {
		
		ArrayList<Doenca> doencas = new ArrayList<>();
		
		doencas = repositorioDoencas.listaDoencas();
		repositorioDoencas.fecharConexao();

		return doencas;
		
		
	}




}
