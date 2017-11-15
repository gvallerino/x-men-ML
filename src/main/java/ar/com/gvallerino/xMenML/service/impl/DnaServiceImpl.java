package ar.com.gvallerino.xMenML.service.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gvallerino.xMenML.dao.DnaDAO;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.service.DnaService;

@Service("dnaService")
public class DnaServiceImpl implements DnaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DnaServiceImpl.class);
	
	@Autowired
	private DnaDAO dnaDAO;
	
	@Override
	public void saveDna(Dna dna) throws SQLException {
		
		try {
			dnaDAO.save(dna);
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al almacenar un ADN", e);
			throw new SQLException();
		}
	}

	@Override
	public long countMutant() throws SQLException {
		try {
			return dnaDAO.countMuntant();
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al recuperar cantidad de mutantes", e);
			throw new SQLException();
		}
	}

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
