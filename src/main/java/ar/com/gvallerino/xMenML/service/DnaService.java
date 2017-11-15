package ar.com.gvallerino.xMenML.service;

import java.sql.SQLException;

import ar.com.gvallerino.xMenML.entities.Dna;

public interface DnaService {
	
	public void saveDna(Dna dna) throws SQLException;
	
	public long countMutant() throws SQLException;
	
	public long countDna() throws SQLException;
}
