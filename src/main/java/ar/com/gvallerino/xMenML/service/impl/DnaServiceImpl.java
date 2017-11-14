package ar.com.gvallerino.xMenML.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gvallerino.xMenML.dao.DnaDAO;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.service.DnaService;

@Service("dnaService")
public class DnaServiceImpl implements DnaService {

	@Autowired
	private DnaDAO dnaDAO;
	
	@Override
	public void saveDna(Dna dna) {
		
		try {
			dnaDAO.save(dna);
		} catch (Exception e) {
			System.out.println(""); //TODO: poner un logger
			//TODO: poner un throws 
		}
	}

	@Override
	public long countMutant() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countDna() {
		return dnaDAO.count();
	}

	public DnaDAO getDnaDAO() {
		return dnaDAO;
	}

	public void setDnaDAO(DnaDAO dnaDAO) {
		this.dnaDAO = dnaDAO;
	}

}
