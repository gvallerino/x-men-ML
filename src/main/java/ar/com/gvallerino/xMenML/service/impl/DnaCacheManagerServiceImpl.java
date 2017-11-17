package ar.com.gvallerino.xMenML.service.impl;

import java.util.Iterator;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Cache.Entry;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gvallerino.xMenML.dao.DnaDAO;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.service.DnaCacheManagerService;

/**
 * Servicio que maneja la memoria cache de ADN.
 * Implementa DnaCacheManagerService
 */
@Service("dnaCacheManagerService")
public class DnaCacheManagerServiceImpl implements DnaCacheManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaCacheManagerService.class);
	
	private static Long idDna = 0L;
	private CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("preConfigured",CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,ResourcePoolsBuilder.heap(100)).build()).build(true);
	private Cache<Long, Dna> myCache = cacheManager.createCache("myCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, Dna.class, ResourcePoolsBuilder.heap(100)).build());

	@Autowired
	private DnaDAO dnaDAO;
	
	/**
	 * Almacena un ADN en cache
	 */
	public void save(Dna dna) {
		idDna++;
		myCache.put(idDna, dna);
	}
	
	/**
	 * Actualiza los ADNs que se encuentran en cache y los persiste en la base de datos.
	 * Limpia la cache.
	 */
	public void updateRegisteredDna() {
		
		Iterator<Entry<Long, Dna>> it = myCache.iterator();
		while (it.hasNext()) {
			Entry<Long, Dna> mapDna = it.next();
			Long key = mapDna.getKey();
			Dna dna = mapDna.getValue();
			
			try {
				LOGGER.error("Guardando en base de datos el ADN: " + key);
				dnaDAO.save(dna);
				myCache.remove(key);
			} catch (Exception e) {
				LOGGER.error("No se pudo almacenar en la base de datos el ADN " + dna.getId());
			}
			idDna = 0L;
		}
	}
	
	/**
	 * Indica si la cache se encuentra vacia.
	 * @return boolean
	 */
	public boolean isEmpty() {
		return myCache == null || !myCache.iterator().hasNext();
	}
	
	public DnaDAO getDnaDAO() {
		return dnaDAO;
	}

	public void setDnaDAO(DnaDAO dnaDAO) {
		this.dnaDAO = dnaDAO;
	}
	
}
