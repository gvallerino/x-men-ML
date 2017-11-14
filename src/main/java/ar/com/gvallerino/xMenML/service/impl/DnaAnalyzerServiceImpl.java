package ar.com.gvallerino.xMenML.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.com.gvallerino.xMenML.entities.Coordinate;
import ar.com.gvallerino.xMenML.entities.HorizontalSequenceHandler;
import ar.com.gvallerino.xMenML.entities.ObliqueLeftSequenceHandler;
import ar.com.gvallerino.xMenML.entities.ObliqueRightSequenceHandler;
import ar.com.gvallerino.xMenML.entities.VerticalSequenceHandler;
import ar.com.gvallerino.xMenML.enums.DnaEnum;
import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;

@Service("dnaAnalyzerService")
public class DnaAnalyzerServiceImpl implements DnaAnalyzerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerServiceImpl.class);
	
	private char[][] matrix;
	private int longMatrix;
	private static int COUNT_DNA_TO_MUTANT = 4;
	List<SequenceHandler> listSequenceHandlers;
	
	public boolean isMutant(String[] dna) {
//		LOGGER.info("Starting task DNA Analyzer");
		
		boolean mutantFound = false;
//		long time_start = System.currentTimeMillis();
		matrix = this.loadMatrix(dna);
		
		if (isValidMatrix()) {
			initializeSequencesAnalyzer();
			mutantFound = searchMutant();
		}
		
//		long time_end = System.currentTimeMillis();
//		LOGGER.info("Completing task DNA Analyzer: " + ( time_end - time_start ) + " milliseconds");
		return mutantFound;
	}
	
	private char[][] loadMatrix(String[] dna) {
		
		char[][]matrix = null;
		
		if (dna != null) {
			longMatrix = dna.length;
			matrix = new char[longMatrix][longMatrix];
			
			for (int i = 0; i < longMatrix; i++) {
				
				if (dna[i].length() != longMatrix) return null; //TODO: Lanzar excepcion
				matrix[i] = dna[i].toCharArray();
			}
		}
		
		return matrix;
	}
	
	private boolean isValidMatrix() {
		return (matrix != null && longMatrix > COUNT_DNA_TO_MUTANT);
	}
	
	private boolean searchMutant() {
		
		int countMutantFound = 0;
		
		for (int i = 0; i < longMatrix; i++) {
			for (int j = 0; j < longMatrix; j++) {
				
				try {
					
					Coordinate coordinate = new Coordinate(i, j);
					char dna = matrix[i][j];
					DnaEnum.belongsToDna(Character.toString(dna));
					
					for (SequenceHandler sequenceHandler : listSequenceHandlers) {
						
						if (sequenceHandler.verifyCoordinates(coordinate) && sequenceHandler.isSequenceMutant(coordinate)) {
							sequenceHandler.addCoordinatesWithoutMoving(coordinate);
							countMutantFound++;
						}
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
		
		listSequenceHandlers = new ArrayList<>();
		listSequenceHandlers.add(new HorizontalSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new VerticalSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new ObliqueRightSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new ObliqueLeftSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		
	}
	
	
//	if (horizontalHandler.verifyCoordinates(coordinate) && horizontalHandler.isSequenceMutant(coordinate)) {
//	horizontalHandler.addCoordinatesWithoutMoving(coordinate);
//	countMutantFound++;
//}
//
//if (verticalHandler.verifyCoordinates(coordinate) && verticalHandler.isSequenceMutant(coordinate)) {
//	verticalHandler.addCoordinatesWithoutMoving(coordinate);
//	countMutantFound++;
//}
//
//if (obliqueRightHandler.verifyCoordinates(coordinate) && obliqueRightHandler.isSequenceMutant(coordinate)) {
//	obliqueRightHandler.addCoordinatesWithoutMoving(coordinate);
//	countMutantFound++;
//}
//
//if (obliqueLeftHandler.verifyCoordinates(coordinate) && obliqueLeftHandler.isSequenceMutant(coordinate)) {
//	obliqueLeftHandler.addCoordinatesWithoutMoving(coordinate);
//	countMutantFound++;
//}
	
//	horizontalHandler = new HorizontalSequenceHandler(matrix, countLettersDna);
//	verticalHandler = new VerticalSequenceHandler(matrix, countLettersDna);
//	obliqueRightHandler = new ObliqueRightSequenceHandler(matrix, countLettersDna);
//	obliqueLeftHandler = new ObliqueLeftSequenceHandler(matrix, countLettersDna);
	
//	private SequenceHandler horizontalHandler;
//	private SequenceHandler verticalHandler;
//	private SequenceHandler obliqueRightHandler;
//	private SequenceHandler obliqueLeftHandler;
	
}
