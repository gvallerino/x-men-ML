package ar.com.gvallerino.xMenML.service;

import ar.com.gvallerino.xMenML.exceptions.DnaCodeException;
import ar.com.gvallerino.xMenML.exceptions.DnaFormatException;

/**
 * Interface del Servicio Analizador de ADN.
 */
public interface DnaAnalyzerService {

	public boolean isMutant(String[] dna) throws DnaCodeException, DnaFormatException;
}
