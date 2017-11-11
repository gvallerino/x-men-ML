package ar.com.gvallerino.xMenML.entities;

import ar.com.gvallerino.xMenML.interfaces.SequenceAnalyzer;

public class VerticalSequenceAnalyzer implements SequenceAnalyzer{
	
	private char[][] matrix;
	private int countLettersDna;
	private int longMatrix;
	
	public VerticalSequenceAnalyzer(char[][] matrix, int countLettersDna) {
		
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
