package ar.com.gvallerino.xMenML.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.gvallerino.xMenML.entities.Coordinate;
import ar.com.gvallerino.xMenML.entities.HorizontalSequenceHandler;
import ar.com.gvallerino.xMenML.entities.ObliqueSequenceHandler;
import ar.com.gvallerino.xMenML.entities.VerticalSequenceHandler;
import ar.com.gvallerino.xMenML.enums.DnaEnum;
import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;

public class DnaAnalyzerServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerServiceImpl.class);
	
	private char[][] matrix;
	private int longMatrix;
	private int countLettersDna = 4;
	
	private SequenceHandler horizontalHandler;
	private SequenceHandler verticalHandler;
	private SequenceHandler obliqueHandler;
	
	public boolean isMutant(String[] dna) {
		LOGGER.info("Starting task DNA Analyzer");
		
		boolean mutantFound = false;
		long time_start = System.currentTimeMillis();
		matrix = this.loadMatrix(dna);
		
		if (isValidMatrix()) {
			initializeSequencesAnalyzer();
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
		
		return matrix;
	}
	
	private boolean isValidMatrix() {
		
		return (matrix != null && longMatrix > countLettersDna);
	}
	
	private boolean searchMutant() {
		
		int countMutantFound = 0;
		
		for (int i = 0; i < longMatrix; i++) {
			for (int j = 0; j < longMatrix; j++) {
				
				try {
					Coordinate coordinate = new Coordinate(i, j);
//					if (j + countLettersDna >= longMatrix) {
//						break;
//					}
					
					DnaEnum.belongsToDna(Character.toString(matrix[i][j]));
					
					if (horizontalHandler.verifyCoordinates(coordinate) && horizontalHandler.isSequenceMutant(coordinate)) {
						horizontalHandler.addCoordinatesWithoutMoving(coordinate);
						countMutantFound++;
					}
					
					if (verticalHandler.verifyCoordinates(coordinate) && verticalHandler.isSequenceMutant(coordinate)) {
						verticalHandler.addCoordinatesWithoutMoving(coordinate);
						countMutantFound++;
					}
					
					if (obliqueHandler.isSequenceMutant(coordinate)) {
						//j += (countLettersDna - 1);
						countMutantFound++;
					}
					
					if (countMutantFound >= 2) {
						return true;
					}
					
				} catch (IllegalArgumentException iae) {
					LOGGER.error("Codigo DNA incorrecto", iae);
					return false;
				}
			}
		}
		return false;
	}
	
	private void initializeSequencesAnalyzer() {
		
		horizontalHandler = new HorizontalSequenceHandler(matrix, countLettersDna);
		verticalHandler = new VerticalSequenceHandler(matrix, countLettersDna);
		obliqueHandler = new ObliqueSequenceHandler(matrix, countLettersDna);
	}
	
}
