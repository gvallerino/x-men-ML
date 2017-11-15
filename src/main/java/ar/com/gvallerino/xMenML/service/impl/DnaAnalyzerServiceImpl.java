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
import ar.com.gvallerino.xMenML.exceptions.DnaCodeException;
import ar.com.gvallerino.xMenML.exceptions.DnaFormatException;
import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;

@Service("dnaAnalyzerService")
public class DnaAnalyzerServiceImpl implements DnaAnalyzerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerServiceImpl.class);
	
	private char[][] matrix;
	private int longMatrix;
	private static int COUNT_DNA_TO_MUTANT = 4;
	List<SequenceHandler> listSequenceHandlers;
	
	public boolean isMutant(String[] dna) throws DnaCodeException, DnaFormatException {
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
	
	private char[][] loadMatrix(String[] dna) throws DnaFormatException {
		
		char[][]matrix = null;
		
		if (dna != null) {
			longMatrix = dna.length;
			matrix = new char[longMatrix][longMatrix];
			
			for (int i = 0; i < longMatrix; i++) {
				
				if (dna[i].length() != longMatrix) throw new DnaFormatException("El formato de la informacion de ADN es incorrecto");
				matrix[i] = dna[i].toCharArray();
			}
		}
		
		return matrix;
	}
	
	private boolean isValidMatrix() {
		return (matrix != null && longMatrix > COUNT_DNA_TO_MUTANT);
	}
	
	private boolean searchMutant() throws DnaCodeException {
		
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
					String errorMessage = "Codigo ADN incorrecto";
					LOGGER.error(errorMessage, iae);
					throw new DnaCodeException(errorMessage);
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
	
}
