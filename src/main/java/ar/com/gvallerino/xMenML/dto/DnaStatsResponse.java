package ar.com.gvallerino.xMenML.dto;

public class DnaStatsResponse {
	
	private long count_mutant_dna;
	private long count_human_dna;
	private long ratio;
	
	public DnaStatsResponse (long mutant, long dna, long ratio) {
		this.count_mutant_dna = mutant;
		this.count_human_dna = dna;
		this.ratio = ratio;
	}
	
	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	
	public void setCount_mutant_dna(long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	
	public long getCount_human_dna() {
		return count_human_dna;
	}
	
	public void setCount_human_dna(long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	
	public long getRatio() {
		return ratio;
	}
	
	public void setRatio(long ratio) {
		this.ratio = ratio;
	}
	
}
