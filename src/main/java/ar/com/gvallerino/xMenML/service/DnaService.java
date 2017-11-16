package ar.com.gvallerino.xMenML.service;

import java.sql.SQLException;

/**
 * Interface del servicio ADN.
 */
public interface DnaService {
	
	public long countMutant() throws SQLException;
	
	public long countDna() throws SQLException;
	
}
