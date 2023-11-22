package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.RepositorioMedicamentos;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;

public class MedicamentoService {
	
	
	private RepositorioMedicamentos repositorioMedicamentos;
	
	public MedicamentoService(RepositorioMedicamentos implMedicamento) {
		this.repositorioMedicamentos = implMedicamento;
	}
	


	public ArrayList<Medicamento> retornaMedicamentos(){
		
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		
		medicamentos = repositorioMedicamentos.listaMedicamentos();
		repositorioMedicamentos.fecharConexao();

		return medicamentos;
		
	}


	

}
