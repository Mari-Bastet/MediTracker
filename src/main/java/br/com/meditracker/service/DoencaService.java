package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.dominio.RepositorioDoenca;
import br.com.meditracker.infra.dao.DoencaDAO;

public class DoencaService {
	
	private RepositorioDoenca repositorioDoenca;
	
	public DoencaService(RepositorioDoenca repositorioDoenca) {
		this.repositorioDoenca = repositorioDoenca;
	}	

	public ArrayList<Doenca> listaDoencas() {
		
		ArrayList<Doenca> doencas = new ArrayList<>();
		
		doencas = repositorioDoenca.listaDoencas();
		repositorioDoenca.fecharConexao();

		return doencas;
		
		
	}




}
