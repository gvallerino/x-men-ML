package ar.com.gvallerino;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.gvallerino.xMenML.entities.DnaAnalyzer;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class XmenMlApplicationTests {

//	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void prueba() {
		DnaAnalyzer dnaAnalyzer = new DnaAnalyzer();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}

}
