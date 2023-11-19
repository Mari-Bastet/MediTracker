package br.com.meditracker.dominio;

import java.time.LocalDate;

public interface ImplTratamentoDiarioPaciente {
	
	public void atualizaRegistroDiarioMed(TratamentoDiarioPaciente tratDiaPaciente, LocalDate dataRegistro);
	public void insereRegistroDiarioTratamento(TratamentoDiarioPaciente tratamentoDiarioPaciente);
}
