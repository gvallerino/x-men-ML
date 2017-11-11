package ar.com.gvallerino.xMenML.entities;

import ar.com.gvallerino.xMenML.interfaces.SequenceAnalyzer;

public class ObliqueSequenceAnalyzer implements SequenceAnalyzer {
	
	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	
	public ObliqueSequenceAnalyzer(char[][] matrix, int countLettersDna) {
		
		this.matrix = matrix;
		this.countLettersDna = countLettersDna;
		this.longMatrix = matrix.length;
	}


	@Override
	public boolean isSequenceMutant(Coordinate actualCoordinate) {
		// TODO Auto-generated method stub
		return false;
	}

}
