package ar.com.gvallerino.xMenML.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gvallerino.xMenML.dao.DnaDAO;
import ar.com.gvallerino.xMenML.dto.DnaRequest;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;

@RestController
public class DnaAnalyzerController {
	
	@Autowired
	@Qualifier("dnaAnalyzerService")
	private DnaAnalyzerService dnaAnalyzerService;
	
	@Autowired
	private DnaDAO dnaDAO;
	
	@PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void isMutant(@RequestBody DnaRequest dnaRequest, HttpServletResponse response) {
		
		try {
			
			String[] dna = dnaRequest.getDna();
			Dna dnaObjct = new Dna();
			dnaObjct.setDnaData("a");
			boolean isMutant = dnaAnalyzerService.isMutant(dna);
			dnaObjct.setMutant(isMutant);
			
			if(isMutant) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			
			dnaDAO.save(dnaObjct);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public DnaAnalyzerService getDnaAnalyzerService() {
		return dnaAnalyzerService;
	}

	public void setDnaAnalyzerService(DnaAnalyzerService dnaAnalyzerService) {
		this.dnaAnalyzerService = dnaAnalyzerService;
	}

	public DnaDAO getDnaDAO() {
		return dnaDAO;
	}

	public void setDnaDAO(DnaDAO dnaDAO) {
		this.dnaDAO = dnaDAO;
	}

}
