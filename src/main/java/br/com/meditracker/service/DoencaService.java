package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.dominio.ImplDoenca;
import br.com.meditracker.infra.dao.DoencaDAO;

public class DoencaService {
	
	private ImplDoenca implDoenca;
	
	public DoencaService(ImplDoenca implDoenca) {
		this.implDoenca = implDoenca;
	}	

	public ArrayList<Doenca> listaDoencas() {
		
		ArrayList<Doenca> doencas = new ArrayList<>();
		
		doencas = implDoenca.listaDoencas();
		implDoenca.fecharConexao();

		return doencas;
		
		
	}




}
