package ar.com.gvallerino.xMenML.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DnaAnalyzer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzer.class);
	
	private char[][] matrix;
	private int longMatrix;
	private int countLettersDna = 4;
	
	public boolean isMutant(String[] dna) {
		
		boolean mutantFound = false;
		
		LOGGER.info("Starting task DNA Analyzer");
		long time_start = System.currentTimeMillis();
		matrix = this.loadMatrix(dna);
		
		if (isValidMatrix()) {
			mutantFound = searchMutant(dna);
		}
		
		long time_end = System.currentTimeMillis();
		LOGGER.info("Completing task DNA Analyzer: " + ( time_end - time_start ) + " milliseconds");
		return mutantFound;
	}
	
	private char[][] loadMatrix(String[] dna) {
		
		longMatrix = dna.length;
		char[][]matrix = new char[longMatrix][longMatrix];
		
		for (int i = 0; i < longMatrix; i++) {
			
			if (dna[i].length() != longMatrix) return null; //TODO: Lanzar excepcion
			matrix[i] = dna[i].toCharArray();
		}
		
		return matrix;
	}
	
	private boolean isValidMatrix() {
		
		return (matrix != null && longMatrix > countLettersDna);
	}
	
	private boolean searchMutant(String[] dna) {
		
		int countMutantFound = 0;
		
		for (int i = 0; i < longMatrix; i++) {

			char[] codeDna = dna[i].toCharArray();
			for (int j = 0; j < longMatrix; j++) {
				
				if (j + countLettersDna >= longMatrix) {
					break;
				}
				
				if (searchSameCode(codeDna, j)) {
					j += (countLettersDna - 1);
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
		
		for (int i = 0; i < countLettersDna - 1; i++) {
			
			if (i >= longMatrix) {
				//TODO: Tirar excepcion
				return false;
			}
			
			char dnaSingle = codeDna[i];
			char dnaSingleNext = codeDna[i+1];
			
			if (dnaSingle != dnaSingleNext) {
				return false;
			}
			
		}
		//TODO: Verificar que las 4 letras iguales sean (A,T,C,G) y no cualquier otra cosa.
		return true;
	}

}
