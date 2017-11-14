package ar.com.gvallerino.xMenML.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gvallerino.xMenML.dto.DnaRequest;
import ar.com.gvallerino.xMenML.dto.DnaStatsResponse;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;
import ar.com.gvallerino.xMenML.service.DnaService;

@RestController
public class DnaAnalyzerController {
	
	@Autowired
	@Qualifier("dnaAnalyzerService")
	private DnaAnalyzerService dnaAnalyzerService;
	
	@Autowired
	@Qualifier("dnaService")
	private DnaService dnaService;
	
	@PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void isMutant(@RequestBody DnaRequest dnaRequest, HttpServletResponse response) {
		
		String[] dna = null;
		boolean isMutant = false;
		
		try {
			dna = dnaRequest.getDna();
			isMutant = dnaAnalyzerService.isMutant(dna);
			
			if(isMutant) {
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			
			saveDna(dna, isMutant);
		
		} catch (Exception e) { //TODO: poner otra exception
			
			System.out.println("Error"); //Poner logger
			saveDna(dna, isMutant);
		}
	}
	
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DnaStatsResponse> stats() {
		
		try {
			
			long mutants = dnaService.countMutant();
			long dnas = dnaService.countDna();
			
			long ratio = -1;
			if (dnas != 0) {
				ratio = mutants / dnas;
			}		
			return ResponseEntity.ok(new DnaStatsResponse(mutants, dnas, ratio));
			
		} catch (Exception e) {
			//TODO: logger
			return ResponseEntity.ok(new DnaStatsResponse(0, 0, -1));
		}
	}
	
	private void saveDna(String[] dna, boolean isMutant) {
		try {
			Dna dnaObject = new Dna();
			dnaObject.setDnaData(Arrays.toString(dna));
			dnaObject.setMutant(isMutant);
			dnaService.saveDna(dnaObject);
			
		} catch (Exception e) { //TODO: Poner bien la excepcion
			System.out.println("Poner logger"); //TODO: poner logger
		}
	}

	public DnaAnalyzerService getDnaAnalyzerService() {
		return dnaAnalyzerService;
	}

	public void setDnaAnalyzerService(DnaAnalyzerService dnaAnalyzerService) {
		this.dnaAnalyzerService = dnaAnalyzerService;
	}

	public DnaService getDnaService() {
		return dnaService;
	}

	public void setDnaService(DnaService dnaService) {
		this.dnaService = dnaService;
	}
	
}
