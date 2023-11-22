package br.com.meditracker.dominio;

import java.util.ArrayList;

public interface RepositorioMedicamento {
	
	 ArrayList<Medicamento> listaMedicamentos();
	
	void fecharConexao();

}
