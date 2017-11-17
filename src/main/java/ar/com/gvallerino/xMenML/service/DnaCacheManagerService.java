package ar.com.gvallerino.xMenML.service;

import ar.com.gvallerino.xMenML.entities.Dna;

/**
 * Interface del servicio que maneja los ADNs analizados en una cache.
 */
public interface DnaCacheManagerService {

	public void save(Dna dna);
	
	public void persistAllDnas();
	
	public boolean isEmpty();
}
