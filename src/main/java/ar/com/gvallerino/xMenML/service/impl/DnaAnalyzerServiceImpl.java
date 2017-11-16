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

/**
 * Servicio Analizador de ADN.
 * Implementa DnaAnalyzerService.
 */
@Service("dnaAnalyzerService")
public class DnaAnalyzerServiceImpl implements DnaAnalyzerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerServiceImpl.class);
	
	private char[][] matrix;
	private int longMatrix;
	private static int COUNT_DNA_TO_MUTANT = 4;
	private static int COUNT_MATCHS_TO_MUTANT = 2;
	private List<SequenceHandler> listSequenceHandlers;
	
	/**
	 * Verifica si un ADN es mutante o no.
	 * @param dna
	 * @return boolean.
	 * @throws DnaCodeException, DnaFormatException
	 */
	public boolean isMutant(String[] dna) throws DnaCodeException, DnaFormatException {
		
		boolean mutantFound = false;
		matrix = this.loadMatrix(dna);
		
		if (isValidMatrix()) {
			initializeSequencesAnalyzer();
			mutantFound = searchMutant();
		}
		
		return mutantFound;
	}
	
	/**
	 * Carga en una matriz el ADN ingresado por parametro.
	 * Verifica que la matriz sea cuadrada.
	 * @param dna
	 * @return matrix
	 * @throws DnaFormatException
	 */
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
	
	/**
	 * Verifica que en la matriz se puede matchear algun ADN mutante.
	 * @return boolean
	 */
	private boolean isValidMatrix() {
		return (matrix != null && longMatrix > COUNT_DNA_TO_MUTANT);
	}
	
	/**
	 * Busca ADN mutante, verificando que cada ADN simple sea valido.
	 * @return boolean
	 * @throws DnaCodeException
	 */
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
					
					if (countMutantFound >= COUNT_MATCHS_TO_MUTANT) {
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
	
	/**
	 * Inicializa todos los sequenceHandlers
	 */
	private void initializeSequencesAnalyzer() {
		
		listSequenceHandlers = new ArrayList<>();
		listSequenceHandlers.add(new HorizontalSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new VerticalSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new ObliqueRightSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		listSequenceHandlers.add(new ObliqueLeftSequenceHandler(matrix, COUNT_DNA_TO_MUTANT));
		
	}
	
}
