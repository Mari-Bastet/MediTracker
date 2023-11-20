package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.ImplMedicamento;
import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;

public class MedicamentoService {
	
	
	private ImplMedicamento implMedicamento;
	

<<<<<<< HEAD
	public MedicamentoService(ImplMedicamento implMedicamento) {
		this.implMedicamento = implMedicamento;
	}
	

=======
>>>>>>> parent of 31ab256 (Ajuste nome de métodos)
	public ArrayList<Medicamento> retornaMedicamentos(){
		
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		
		medicamentos = implMedicamento.listaMedicamentos();
		implMedicamento.fecharConexao();

		return medicamentos;
		
	}


	

}
