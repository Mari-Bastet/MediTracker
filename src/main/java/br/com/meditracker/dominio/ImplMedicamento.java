package br.com.meditracker.dominio;

import java.util.ArrayList;

public interface ImplMedicamento {
	
	 ArrayList<Medicamento> listaMedicamentos();
	
	void fecharConexao();

}
