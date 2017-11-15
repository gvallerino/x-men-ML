package ar.com.gvallerino.xMenML.service;

import ar.com.gvallerino.xMenML.exceptions.DnaCodeException;
import ar.com.gvallerino.xMenML.exceptions.DnaFormatException;

public interface DnaAnalyzerService {

	public boolean isMutant(String[] dna) throws DnaCodeException, DnaFormatException;
}
