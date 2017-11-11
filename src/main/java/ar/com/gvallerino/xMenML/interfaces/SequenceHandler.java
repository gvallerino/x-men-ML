package ar.com.gvallerino.xMenML.interfaces;

import ar.com.gvallerino.xMenML.entities.Coordinate;

public interface SequenceHandler {
	
	public boolean isSequenceMutant(Coordinate currentCoordinate);
	public void addCoordinatesWithoutMoving(Coordinate currentCoordinate);
	public boolean verifyCoordinates(Coordinate currentCoordinate);

}
