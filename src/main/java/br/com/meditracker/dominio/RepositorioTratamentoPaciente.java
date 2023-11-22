package br.com.meditracker.dominio;

import java.time.LocalDate;
import java.util.ArrayList;

public interface RepositorioTratamentoPaciente {
	
	void insereTratamentoPaciente(TratamentoPaciente tratamentoPaciente, String documentoPaciente);
	
	ArrayList<TratamentoPaciente> listaTratamentosDoDia(String documentoPaciente, LocalDate dataRegistro);
	
	void fecharConexao();
}
