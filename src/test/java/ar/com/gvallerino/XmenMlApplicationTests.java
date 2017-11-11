package ar.com.gvallerino;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.gvallerino.xMenML.service.impl.DnaAnalyzerServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class XmenMlApplicationTests {

//	@Test
	public void contextLoads() {
		
	}
	
	//TODO: Calcular el tiempo de corrida de todos los tests juntos
	
	@Test
	public void testRowWithIncompleteInformationShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testColumnWithIncompleteInformationShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneDnaIsNotCorrectShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","AAAACC","TTUTGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGG","AATTCC","ATATGC","AGAAGC","CCCCTC","TCACTC"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}

	@Test
	public void testMatchHorizontalAndOblicuoSequenceShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
}
