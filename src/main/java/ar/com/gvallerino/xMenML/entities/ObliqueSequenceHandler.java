package ar.com.gvallerino.xMenML.entities;

import ar.com.gvallerino.xMenML.interfaces.SequenceHandler;

public class ObliqueSequenceHandler implements SequenceHandler {
	
	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	
	public ObliqueSequenceHandler(char[][] matrix, int countLettersDna) {
		
		this.matrix = matrix;
		this.countLettersDna = countLettersDna;
		this.longMatrix = matrix.length;
	}


	@Override
	public boolean isSequenceMutant(Coordinate currentCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void addCoordinatesWithoutMoving(Coordinate currentCoordinate) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean verifyCoordinates(Coordinate currentCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
