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
	
	// Tests de comprobacion de datos
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
	public void testOneDnaIsIncorrectShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","AAAACC","TTUTGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	// Test de matcheo de mutante horizontales
	@Test
	public void testOneMatchHorizontalSequenceShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","AATACC","TTATGT","AGAGGG","CCCCTA","TCACTG"};
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
	public void testOneMatchHorizontalSequenceFiveDnaShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","AAAAAC","TTATGT","AGAGGG","CTCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchHorizontalSequenceInLimitsShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceInLimitsShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchHorizontalSequenceCorrelativesShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"AAAAAAAT","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceCorrelativesShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"AAAAAAAA","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	// Test de matcheo de mutante verticales
	@Test
	public void testTwoMatchsVerticalSequenceShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGG","AATTCC","ATATGC","AGAAGC","CCCCTC","TCACTC"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchVerticalSequenceFiveDnaShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","ATTAAC","TTATGT","ATAAGG","CTCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchVerticalSequenceInLimitsShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTT"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceInLimitsShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTC"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchVerticalSequenceCorrelativesShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"AGACATAT","AGTGTGTG","ATCTCTCT","AGCGCGCG","ATATATAT","ACACACAC","AACACACA","AAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceCorrelativesShouldBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"AAAAAAAA","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	// Test de matcheo de mutante oblicuos
	@Test
	public void testOneMatchObliqueRightSequenceShouldNotBeMutant() {
		DnaAnalyzerServiceImpl dnaAnalyzer = new DnaAnalyzerServiceImpl();
		String[] dna = {"ATGCGA","CAGTCC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
}
