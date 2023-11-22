package br.com.meditracker.dominio;

import java.util.ArrayList;

public interface RepositorioDoenca {
	
	public ArrayList<Doenca> listaDoencas();
	
	void fecharConexao();
}
