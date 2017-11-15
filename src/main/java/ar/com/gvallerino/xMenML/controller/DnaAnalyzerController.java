package ar.com.gvallerino.xMenML.controller;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gvallerino.xMenML.dto.DnaRequest;
import ar.com.gvallerino.xMenML.dto.DnaStatsResponse;
import ar.com.gvallerino.xMenML.entities.Dna;
import ar.com.gvallerino.xMenML.exceptions.DnaCodeException;
import ar.com.gvallerino.xMenML.exceptions.DnaFormatException;
import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;
import ar.com.gvallerino.xMenML.service.DnaService;

@RestController
public class DnaAnalyzerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DnaAnalyzerController.class);
	
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
		} catch (DnaCodeException dce) {
			LOGGER.error(dce.getMessage(), dce);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} catch (DnaFormatException dfe) {
			LOGGER.error(dfe.getMessage(), dfe);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} catch (Exception e) {
			LOGGER.error("Se produjo un error al realizar la validacion de mutante", e);
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

		saveDna(dna, isMutant);
	}
	
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DnaStatsResponse> stats() {
		
		try {
			
			double mutants = dnaService.countMutant();
			double dnas = dnaService.countDna();
			double ratio = mutants / dnas;
			
			return ResponseEntity.ok(new DnaStatsResponse(mutants, dnas, ratio));
			
		} catch (SQLException sqle) {
			LOGGER.error("Se produjo un error al realizar la conexion con la base de datos", sqle);
//			return ResponseEntity.ok(new DnaStatsResponse(0, 0, -1));
			return new ResponseEntity<DnaStatsResponse>(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
		} catch (ArithmeticException ae) {
			LOGGER.error("Se produjo un error al realizar el calculo de ratio", ae);
//			return ResponseEntity.ok(new DnaStatsResponse(0, 0, -1));
			return new ResponseEntity<DnaStatsResponse>(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			LOGGER.error("Se produjo un error en el servicio", e);
//			return ResponseEntity.ok(new DnaStatsResponse(0, 0, -1));
			return new ResponseEntity<DnaStatsResponse>(null,new HttpHeaders(),HttpStatus.FORBIDDEN);
		}
	}
	
	private void saveDna(String[] dna, boolean isMutant) {
		try {
			Dna dnaObject = new Dna();
			dnaObject.setDnaData(Arrays.toString(dna));
			dnaObject.setMutant(isMutant);
			dnaService.saveDna(dnaObject);
			
		} catch (SQLException sqle) {
			LOGGER.error("Se produjo un error al querer almacenar el ADN", sqle);
		} catch (Exception e) {
			LOGGER.error("Se produjo un error en el servicio", e);
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
