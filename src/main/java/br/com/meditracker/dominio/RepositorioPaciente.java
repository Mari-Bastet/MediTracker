package br.com.meditracker.dominio;

public interface RepositorioPaciente {
	
	 Paciente realizaLogin(String documento_paciente, String senha_paciente);
	
	 int retornaIdPaciente(String documento_paciente);
	
	 void cadastraPaciente(Paciente paciente);
	
	 void fecharConexao();
}
