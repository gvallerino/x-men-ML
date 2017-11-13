package ar.com.gvallerino.xMenML.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gvallerino.xMenML.dto.DnaRequest;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;
import ar.com.gvallerino.xMenML.service.impl.DnaAnalyzerServiceImpl;

@RestController
public class DnaAnalyzerController {
	
	DnaAnalyzerService dnaAnalyzer = new DnaAnalyzerServiceImpl();
	
	@PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void isMutant(@RequestBody DnaRequest dnaRequest, HttpServletResponse response) {
		
		String[] dna = dnaRequest.getDna();
		if(dnaAnalyzer.isMutant(dna)) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
}
