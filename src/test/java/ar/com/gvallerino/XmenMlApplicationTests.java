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
	
	//TODO: Calcular el tiempo de corrida de todos los tests juntos
	
	@Test
	public void testRowWithIncompleteInformationShouldNotBeMutant() {
		DnaAnalyzer dnaAnalyzer = new DnaAnalyzer();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testColumnWithIncompleteInformationShouldNotBeMutant() {
		DnaAnalyzer dnaAnalyzer = new DnaAnalyzer();
		String[] dna = {"ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchCodeDnaShouldNotBeMutant() {
		DnaAnalyzer dnaAnalyzer = new DnaAnalyzer();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchCodeDnaShouldBeMutant() {
		DnaAnalyzer dnaAnalyzer = new DnaAnalyzer();
		String[] dna = {"ATGCGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}

}
