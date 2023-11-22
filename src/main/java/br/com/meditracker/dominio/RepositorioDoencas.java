package br.com.meditracker.dominio;

import java.util.ArrayList;

public interface RepositorioDoencas {
	
	public ArrayList<Doenca> listaDoencas();
	
	void fecharConexao();
}
