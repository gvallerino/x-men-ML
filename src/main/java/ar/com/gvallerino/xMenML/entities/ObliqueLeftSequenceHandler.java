package ar.com.gvallerino.xMenML.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.gvallerino.xMenML.enums.DnaEnum;
import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;

/**
 * Clase que se encarga de manejar los matcheos de las secuencias oblicuas-izquierda.
 */
public class ObliqueLeftSequenceHandler implements SequenceHandler {

	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	private List<Coordinate> coordinatesWithoutMoving = new ArrayList<>();
	
	public ObliqueLeftSequenceHandler(char[][] matrix, int countLettersDna) {
		this.matrix = matrix;
		this.countLettersDna = countLettersDna;
		this.longMatrix = matrix.length;
	}
	
	/**
	 * Implementacion de isSequenceMutant para coordenadas oblicuas-izquierda.
	 */
	@Override
	public boolean isSequenceMutant(Coordinate currentCoordinate) {
		
		int j = currentCoordinate.getY();
		int upperLimit = currentCoordinate.getX() + countLettersDna - 1;
		
		for (int i = currentCoordinate.getX(); i < upperLimit; i++) {
			
			if (i+1 >= longMatrix || j >= longMatrix || j-1 < 0) {
				return false;
			}
			
			char dna = matrix[i][j];
			char dnaNext = matrix[i+1][j-1];
			
			DnaEnum.belongsToDna(Character.toString(dna));
			DnaEnum.belongsToDna(Character.toString(dnaNext));
			
			if (dna != dnaNext) {
				return false;
			}
			
			j--;
		}
		
		return true;
	}

	/**
	 * Agrega la coordenada a la lista de coordenadas que no se deben verificar.
	 */
	@Override
	public void addCoordinatesWithoutMoving(Coordinate currentCoordinate) {

		for (int i = 1; i < countLettersDna; i++) {
			Coordinate coordinate = new Coordinate(currentCoordinate.getX() + i, currentCoordinate.getY() - i);
			coordinatesWithoutMoving.add(coordinate);
		}
	}

	/**
	 * Se consulta si la coordenada debe ser verificada o no.
	 */
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
