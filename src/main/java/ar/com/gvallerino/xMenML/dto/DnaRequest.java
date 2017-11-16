package ar.com.gvallerino.xMenML.dto;

/**
 * Clase que representa el request que se envía en el body del servicio REST.
 */
public class DnaRequest {

	/** Request */
	String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
}
