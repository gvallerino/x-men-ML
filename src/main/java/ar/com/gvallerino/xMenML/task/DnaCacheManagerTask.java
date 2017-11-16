package ar.com.gvallerino.xMenML.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.gvallerino.xMenML.service.DnaCacheManagerService;

/**
 * Clase que representa la tarea para manejar la cache en memoria.
 */
@Component
public class DnaCacheManagerTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaCacheManagerTask.class);
	
	@Autowired
	private DnaCacheManagerService dnaCacheManagerService;
	
	/**
	 * Tarea que almacena en la base de datos todos los ADNs guardados en cache.
	 * @throws InterruptedException
	 */
	@Scheduled(fixedRate = 30000)
    public void saveDnasInCache() throws InterruptedException {
		LOGGER.info("Inicializando task | Guardando ADNs en Cache");
		
		if (!dnaCacheManagerService.isEmpty()) {
			dnaCacheManagerService.updateRegisteredDna();
		}
		LOGGER.info("Finalizando task | Guardando ADNs en Cache");
    }

	public DnaCacheManagerService getDnaCacheManagerService() {
		return dnaCacheManagerService;
	}

	public void setDnaCacheManagerService(DnaCacheManagerService dnaCacheManagerService) {
		this.dnaCacheManagerService = dnaCacheManagerService;
	}

}
