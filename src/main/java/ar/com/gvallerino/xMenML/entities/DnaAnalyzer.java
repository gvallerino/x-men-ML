package ar.com.gvallerino.xMenML.entities;

public class DnaAnalyzer {
	
	private String[] dna;
	private int rows;
	private int columns;
	private int countLettersDna = 4;
	
	public boolean isMutant(String[] dna) {
		
		this.dna = dna;
		this.rows = dna.length;
		this.columns = dna[0].length();
		
		if (isSquareRange(dna)) {
			
			//TODO: Poner contador de tiempo
			boolean mutantFound = searchMutant(dna);
		}
		
		return false;
	}
	
	private boolean isSquareRange(String[] dna) {
		
		if (dna != null && dna.length > 1) {
			
			return rows == columns;
		}
		
		//TODO: Tirar excepcion cuando algo falle
		return false;
	}
	
	private boolean searchMutant(String[] dna) {
		
		int countMutantFound = 0;
		
		for (int i = 0; i < columns; i++) {

			char[] codeDna = dna[i].toCharArray();
			for (int j = 0; j < rows; j++) {
				
				if (searchSameCode(codeDna, j)) {
					countMutantFound++;
					
					if (countMutantFound >= 2) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean searchSameCode(char[] codeDna, int actualPosition) {
		
		//TODO: poner en el for de arriba, para el break
		if (actualPosition + countLettersDna >= columns) {
			return false;
		}
		
		for (int i = 0; i < countLettersDna; i++) {
			
			if (i >= columns) {
				//TODO: Tirar excepcion
				return false;
			}
			
			char dnaSingle = codeDna[i];
			char dnaSingleNext = codeDna[i+1];
			
			if (dnaSingle != dnaSingleNext) {
				return false;
			}
			
		}
		
		return true;
	}

}
