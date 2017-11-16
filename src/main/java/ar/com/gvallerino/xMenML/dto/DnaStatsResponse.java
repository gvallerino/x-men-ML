package ar.com.gvallerino.xMenML.dto;

/**
 * Clase que representa el response del servicio 'stats' de la API.
 */
public class DnaStatsResponse {
	
	/** Cantidad de mutantes */
	private double count_mutant_dna;
	
	/** Cantidad de humanos */
	private double count_human_dna;
	
	/** Proporcion mutantes / humanos */
	private double ratio;
	
	/** Constructor */
	public DnaStatsResponse (double mutant, double dna, double ratio) {
		this.count_mutant_dna = mutant;
		this.count_human_dna = dna;
		this.ratio = ratio;
	}
	
	public double getCount_mutant_dna() {
		return count_mutant_dna;
	}
	
	public void setCount_mutant_dna(double count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	
	public double getCount_human_dna() {
		return count_human_dna;
	}
	
	public void setCount_human_dna(double count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
}
