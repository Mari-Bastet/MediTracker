package br.com.meditracker.dominio;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Paciente {
	
	 @JsonProperty
	  private int idPaciente;  

	 @JsonProperty
	  private String nomePaciente;

	  @JsonProperty 
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "UTC")
	  private LocalDate dataNascimentoPaciente;

	  @JsonProperty
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

		public String getNomePaciente() {
			return nomePaciente;
		}

		public LocalDate getDataNascimentoPaciente() {
	       return dataNascimentoPaciente;
	        
		}

		public String getDocumentoPaciente() {
			return documentoPaciente;
		}

		public String getSenhaPaciente() {
			return senhaPaciente;
		}

		public String getEmailPaciente() {
			return emailPaciente;
		}


}
