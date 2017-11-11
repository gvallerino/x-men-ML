package ar.com.gvallerino.xMenML.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.gvallerino.xMenML.entities.Coordinate;
import ar.com.gvallerino.xMenML.entities.HorizontalSequenceAnalyzer;
import ar.com.gvallerino.xMenML.entities.ObliqueSequenceAnalyzer;
import ar.com.gvallerino.xMenML.entities.VerticalSequenceAnalyzer;
import ar.com.gvallerino.xMenML.interfaces.SequenceAnalyzer;

public class DnaAnalyzerServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerServiceImpl.class);
	
	private char[][] matrix;
	private int longMatrix;
	private int countLettersDna = 4;
	
	private SequenceAnalyzer horizontalSequenceAnalyzer;
	private SequenceAnalyzer verticalSequenceAnalyzer;
	private SequenceAnalyzer obliqueSequenceAnalyzer;
	
	public boolean isMutant(String[] dna) {
		
		boolean mutantFound = false;
		
		LOGGER.info("Starting task DNA Analyzer");
		long time_start = System.currentTimeMillis();
		matrix = this.loadMatrix(dna);
		
		if (isValidMatrix()) {
			mutantFound = searchMutant();
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
		
		horizontalSequenceAnalyzer = new HorizontalSequenceAnalyzer(matrix, countLettersDna);
		verticalSequenceAnalyzer = new VerticalSequenceAnalyzer(matrix, countLettersDna);
		obliqueSequenceAnalyzer = new ObliqueSequenceAnalyzer(matrix, countLettersDna);
		
		return matrix;
	}
	
	private boolean isValidMatrix() {
		
		return (matrix != null && longMatrix > countLettersDna);
	}
	
	private boolean searchMutant() {
		
		int countMutantFound = 0;
		
		for (int i = 0; i < longMatrix; i++) {
			for (int j = 0; j < longMatrix; j++) {
				
				Coordinate coordinate = new Coordinate(i, j);
				if (j + countLettersDna >= longMatrix) {
					break;
				}
				
				if (horizontalSequenceAnalyzer.isSequenceMutant(coordinate)) {
					j += (countLettersDna - 1); //Esto estaria mal
					countMutantFound++;
				}
				
				if (verticalSequenceAnalyzer.isSequenceMutant(coordinate)) {
					//j += (countLettersDna - 1); //TODO: pensar bien esto
					countMutantFound++;
				}
				
				if (obliqueSequenceAnalyzer.isSequenceMutant(coordinate)) {
					//j += (countLettersDna - 1);
					countMutantFound++;
				}
				
				if (countMutantFound >= 2) {
					return true;
				}
			}
		}
		return false;
	}
	
//	private boolean searchSameCode(char[] codeDna, int actualPosition) {
//		
//		for (int i = 0; i < countLettersDna - 1; i++) {
//			
//			if (i >= longMatrix) {
//				//TODO: Tirar excepcion
//				return false;
//			}
//			
//			char dnaSingle = codeDna[i];
//			char dnaSingleNext = codeDna[i+1];
//			
//			if (dnaSingle != dnaSingleNext) {
//				return false;
//			}
//			
//		}
//		//TODO: Verificar que las 4 letras iguales sean (A,T,C,G) y no cualquier otra cosa.
//		return true;
//	}

}
