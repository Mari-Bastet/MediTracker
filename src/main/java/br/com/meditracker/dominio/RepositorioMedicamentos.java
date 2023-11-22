package br.com.meditracker.dominio;

import java.util.ArrayList;

public interface RepositorioMedicamentos {
	
	 ArrayList<Medicamento> listaMedicamentos();
	
	void fecharConexao();

}
