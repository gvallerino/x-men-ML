package ar.com.gvallerino.xMenML.entities;

import ar.com.gvallerino.xMenML.enums.DnaEnum;
import ar.com.gvallerino.xMenML.interfaces.SequenceAnalyzer;

public class HorizontalSequenceAnalyzer implements SequenceAnalyzer {
	
	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	
	public HorizontalSequenceAnalyzer(char[][] matrix, int countLettersDna) {
		
		this.matrix = matrix;
		this.countLettersDna = countLettersDna;
		this.longMatrix = matrix.length;
	}
	
	@Override
	public boolean isSequenceMutant(Coordinate currentCoordinate) {
		
		int i = currentCoordinate.getX();
		
		for (int j = 0; j < countLettersDna - 1; j++) {
			
			if (j >= longMatrix) {
				//TODO: Tirar excepcion
				return false;
			}
			
			if (matrix[i][j] != matrix[i][j+1]) {
				return false;
			}
			
		}
		
		int j = currentCoordinate.getY();
		return DnaEnum.belongsToDna(Character.toString(matrix[i][j]));
	}

}
