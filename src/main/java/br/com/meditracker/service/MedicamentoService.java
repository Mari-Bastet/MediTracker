package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.ImplMedicamento;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;

public class MedicamentoService {
	
	
	private ImplMedicamento implMedicamento;
	

	public MedicamentoService(ImplMedicamento implMedicamento) {
		this.implMedicamento = implMedicamento;
	}
	

	public ArrayList<Medicamento> listaMedicamentos(){
		
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		
		medicamentos = implMedicamento.listaMedicamentos();
		implMedicamento.fecharConexao();

		return medicamentos;
		
	}


	

}
