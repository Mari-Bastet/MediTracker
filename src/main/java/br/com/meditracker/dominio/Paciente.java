package br.com.meditracker.dominio;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paciente {
	
	 @JsonProperty
	  private int id_paciente;  

	 @JsonProperty
	  private String nome_paciente;

	  @JsonProperty           
	  private Date data_nascimento_paciente;

	  @JsonProperty 
	  private String documento_paciente ;

	  @JsonProperty     
	  private String senha_paciente;

	  @JsonProperty         
	  private String email_paciente;
	
	  public Paciente(int id_paciente, String nome_paciente, Date data_nascimento_paciente, String documento_paciente,
			String senha_paciente, String email_paciente) {

		this.id_paciente = id_paciente;
		this.nome_paciente = nome_paciente;
		this.data_nascimento_paciente = data_nascimento_paciente;
		this.documento_paciente = documento_paciente;
		this.senha_paciente = senha_paciente;
		this.email_paciente = email_paciente;
	}
	  
	  public int getId_paciente() {
			return id_paciente;
		}

		public void setId_paciente(int id_paciente) {
			this.id_paciente = id_paciente;
		}

		public String getNome_paciente() {
			return nome_paciente;
		}

		public void setNome_paciente(String nome_paciente) {
			this.nome_paciente = nome_paciente;
		}

		public Date getData_nascimento_paciente() {
			return data_nascimento_paciente;
		}

		public void setData_nascimento_paciente(Date data_nascimento_paciente) {
			this.data_nascimento_paciente = data_nascimento_paciente;
		}

		public String getDocumento_paciente() {
			return documento_paciente;
		}

		public void setDocumento_paciente(String documento_paciente) {
			this.documento_paciente = documento_paciente;
		}

		public String getSenha_paciente() {
			return senha_paciente;
		}

		public void setSenha_paciente(String senha_paciente) {
			this.senha_paciente = senha_paciente;
		}

		public String getEmail_paciente() {
			return email_paciente;
		}

		public void setEmail_paciente(String email_paciente) {
			this.email_paciente = email_paciente;
		}

	  
	  
}
