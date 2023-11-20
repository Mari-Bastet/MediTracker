package br.com.meditracker.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Paciente {
	
	 @JsonProperty
	  private int idPaciente;  

	  private String nomePaciente;

	  @JsonProperty 
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
	  private LocalDate dataNascimentoPaciente;

	  private String documentoPaciente ;

	  @JsonProperty     
	  private String senhaPaciente;

	  @JsonProperty         
	  private String emailPaciente;
	
	  public Paciente(int idPaciente, String nomePaciente, LocalDate dataNascimentoPaciente, String documentoPaciente,
			String senhaPaciente, String emailPaciente) {

		this.idPaciente = idPaciente;
		this.nomePaciente = nomePaciente;
		this.dataNascimentoPaciente = dataNascimentoPaciente;
		this.documentoPaciente = documentoPaciente;
		this.senhaPaciente = senhaPaciente;
		this.emailPaciente = emailPaciente;
	}
	  
	  public Paciente() {}
	  
	  public int getIdPaciente() {
			return idPaciente;
		}

		public void setIdPaciente(int idPaciente) {
			this.idPaciente = idPaciente;
		}

		public String getNomePaciente() {
			return nomePaciente;
		}

		public void setNomePaciente(String nomePaciente) {
			this.nomePaciente = nomePaciente;
		}

		public LocalDate getDataNascimentoPaciente() {
	       return dataNascimentoPaciente;
	        
		}

		public void setDataNascimentoPaciente(LocalDate dataNascimentoPaciente) {
			this.dataNascimentoPaciente = dataNascimentoPaciente;
		}

		public String getDocumentoPaciente() {
			return documentoPaciente;
		}

		public void setDocumentoPaciente(String documentoPaciente) {
			this.documentoPaciente = documentoPaciente;
		}

		public String getSenhaPaciente() {
			return senhaPaciente;
		}

		public void setSenhaPaciente(String senhaPaciente) {
			this.senhaPaciente = senhaPaciente;
		}

		public String getEmailPaciente() {
			return emailPaciente;
		}

		public void setEmailPaciente(String emailPaciente) {
			this.emailPaciente = emailPaciente;
		}
}
