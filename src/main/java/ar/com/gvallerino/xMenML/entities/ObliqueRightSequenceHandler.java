package ar.com.gvallerino.xMenML.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.gvallerino.xMenML.enums.DnaEnum;
import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;

public class ObliqueRightSequenceHandler implements SequenceHandler {
	
	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	private List<Coordinate> coordinatesWithoutMoving = new ArrayList<>();
	
	public ObliqueRightSequenceHandler(char[][] matrix, int countLettersDna) {
		
		this.matrix = matrix;
		this.countLettersDna = countLettersDna;
		this.longMatrix = matrix.length;
	}


	@Override
	public boolean isSequenceMutant(Coordinate currentCoordinate) {

		int i = currentCoordinate.getX();
		int upperLimit = currentCoordinate.getY() + countLettersDna - 1;
		
		for (int j = currentCoordinate.getY(); j < upperLimit; j++) {
			
			if (i+1 >= longMatrix || j+1 >= longMatrix) {
				//TODO: Tirar excepcion 
				return false;
			}
			
			char dna = matrix[i][j];
			char dnaNext = matrix[i+1][j+1];
			
			DnaEnum.belongsToDna(Character.toString(dna));
			DnaEnum.belongsToDna(Character.toString(dnaNext));
			
			if (dna != dnaNext) {
				return false;
			}
			
			i++;
		}
		
		return true;
	}


	@Override
	public void addCoordinatesWithoutMoving(Coordinate currentCoordinate) {
		
		for (int i = 1; i < countLettersDna; i++) {
			Coordinate coordinate = new Coordinate(currentCoordinate.getX() + i, currentCoordinate.getY() + i);
			coordinatesWithoutMoving.add(coordinate);
		}
	}


	@Override
	public boolean verifyCoordinates(Coordinate currentCoordinate) {
		
		if (coordinatesWithoutMoving != null && !coordinatesWithoutMoving.isEmpty()) {
			for (Coordinate coordinate : coordinatesWithoutMoving) {
				if (coordinate.equals(currentCoordinate)) {
					return false;
				}
			}
		}
		
		return true;
	}

}
