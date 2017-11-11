package ar.com.gvallerino.xMenML.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gvallerino.xMenML.service.impl.DnaAnalyzerServiceImpl;

@RestController
public class DnaAnalyzerController {
	
	DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
	
	@RequestMapping("/mutant")
	public void isMutant(HttpServletResponse response) {
		
		String[] dna = null;
		if(dnaAnalyzer.isMutant(dna)) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		}else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
}
