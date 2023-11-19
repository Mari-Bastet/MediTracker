package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Doenca;
import br.com.meditracker.dominio.ImplDoenca;
import br.com.meditracker.infra.dao.DoencaDAO;

public class DoencaService implements ImplDoenca {
	
	DoencaDAO doencaDAO = new DoencaDAO();

	public ArrayList<Doenca> listaDoencas() {
		
		ArrayList<Doenca> doencas = new ArrayList<>();
		
		doencas = doencaDAO.listaDoencas();
		doencaDAO.fecharConexao();

		return doencas;
		
		
	}
}
