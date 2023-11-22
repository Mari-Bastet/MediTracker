package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.RepositorioMedicamento;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;

public class MedicamentoService {
	
	
	private RepositorioMedicamento repositorioMedicamento;
	
	public MedicamentoService(RepositorioMedicamento repositorioMedicamento) {
		this.repositorioMedicamento = repositorioMedicamento;
	}
	


	public ArrayList<Medicamento> retornaMedicamentos(){
		
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		
		medicamentos = repositorioMedicamento.listaMedicamentos();
		repositorioMedicamento.fecharConexao();

		return medicamentos;
		
	}


	

}
