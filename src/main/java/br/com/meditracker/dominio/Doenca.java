package br.com.meditracker.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Doenca {
	
	@JsonProperty
	private int idDoenca;

	@JsonProperty
	private String nomeDoenca;

	@JsonProperty
	private String cidDoenca;

	@JsonProperty
	private int escopoDoenca;

	public Doenca(int idDoenca, String nomeDoenca, String cidDoenca, int escopoDoenca) {
		this.idDoenca = idDoenca;
		this.nomeDoenca = nomeDoenca;
		this.cidDoenca = cidDoenca;
		this.escopoDoenca = escopoDoenca;
	}
	
	public Doenca() {}

	public int getIdDoenca() {
		return idDoenca;
	}

	public void setIdDoenca(int idDoenca) {
		this.idDoenca = idDoenca;
	}

	public String getNomeDoenca() {
		return nomeDoenca;
	}

	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}

	public String getCidDoenca() {
		return cidDoenca;
	}

	public void setCidDoenca(String cidDoenca) {
		this.cidDoenca = cidDoenca;
	}

	public int getEscopoDoenca() {
		return escopoDoenca;
	}

	public void setEscopoDoenca(int escopoDoenca) {
		this.escopoDoenca = escopoDoenca;
	}	

}
