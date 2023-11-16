package br.com.meditracker.service;

import java.util.ArrayList;

import br.com.meditracker.dominio.Medicamento;
import br.com.meditracker.infra.dao.MedicamentoDAO;

public class MedicamentoService {

	public ArrayList<Medicamento> retornaMedicamentos(){
		MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
		
		ArrayList<Medicamento> medicamentos = new ArrayList<>();
		
		medicamentos = medicamentoDAO.listaMedicamentos();
		
		medicamentoDAO.fecharConexao();

		return medicamentos;
		
	}
	
	

}
