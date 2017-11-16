package ar.com.gvallerino.xMenML.service.impl;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gvallerino.xMenML.dao.DnaDAO;
import ar.com.gvallerino.xMenML.service.DnaService;

/**
 * Servicio de ADN
 * Implementa DnaService
 */
@Service("dnaService")
public class DnaServiceImpl implements DnaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceImpl.class);
	
	@Autowired
	private DnaDAO dnaDAO;
	
	/**
	 * Devuelve la cantidad de mutantes registrados en base de datos
	 * @return long
	 */
	@Override
	public long countMutant() throws SQLException {
		try {
			return dnaDAO.countMuntant();
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al recuperar cantidad de mutantes", e);
			throw new SQLException();
		}
	}

	/**
	 * Devuelve la cantidad de humanos registrados en base de datos
	 * @return long
	 */
	@Override
	public long countDna() throws SQLException {
		
		try {
			return dnaDAO.count();
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al recuperar cantidad de humanos", e);
			throw new SQLException();
		}
	}
	
	public DnaDAO getDnaDAO() {
		return dnaDAO;
	}

	public void setDnaDAO(DnaDAO dnaDAO) {
		this.dnaDAO = dnaDAO;
	}

}
