package br.com.meditracker.dominio;

import java.time.LocalDate;

public interface ImplTratamentoDiarioPaciente {
	
	public void atualizaRegistroDiarioMed(TratamentoDiarioPaciente tratDiaPaciente, LocalDate dataRegistro);
	
	public void insereRegistroDiarioTratamento(TratamentoDiarioPaciente tratamentoDiarioPaciente);
	
	int verificaStaRemedio(TratamentoDiarioPaciente tratDiaPaciente, LocalDate dataRegistro);

	Boolean verificaExistenciaRegistroDiario(int idTratMedPaciente
			                                       ,LocalDate dataRegistro
			                                       ,String documentoPaciente);

	void fecharConexao();
}
