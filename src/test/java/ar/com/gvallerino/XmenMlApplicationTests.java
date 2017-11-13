package ar.com.gvallerino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.gvallerino.xMenML.service.DnaAnalyzerService;
import ar.com.gvallerino.xMenML.service.impl.DnaAnalyzerServiceImpl;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class XmenMlApplicationTests {
	
	private DnaAnalyzerService dnaAnalyzer;

//	@Test
	public void contextLoads() {
		
	}
	
	@Before
	public void initialize() {
		dnaAnalyzer = new DnaAnalyzerServiceImpl();
	}
	
	
	/***********************   Tests de validación de datos   ***********************/
	@Test
	public void testRowWithIncompleteInformationShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testColumnWithIncompleteInformationShouldNotBeMutant() {
		String[] dna = {"ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneDnaIsIncorrectShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AAAACC","TTUTGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	/***********************   Tests de mutantes con ADN Horizontal   ***********************/
	@Test
	public void testOneMatchHorizontalSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AATACC","TTATGT","AGAGGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchHorizontalSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AAAAAC","TTATGT","AGAGGG","CTCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchHorizontalSequenceInLimitsShouldNotBeMutant() {
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchHorizontalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AAAAAAAT","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AAAAAAAA","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	/***********************   Tests de mutantes con ADN Vertical   ***********************/
	@Test
	public void testTwoMatchsVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGG","AATTCC","ATATGC","AGAAGC","CCCCTC","TCACTC"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testOneMatchVerticalSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","ATTAAC","TTATGT","ATAAGG","CTCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchVerticalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTT"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTC"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AGACATAT","AGTGTGTG","ATCTCTCT","AGCGCGCG","ATATATAT","ACACACAC","AACACACA","AAGAGAGA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	/***********************   Tests de mutantes con ADN Oblicuo Derecho   ***********************/
	@Test
	public void testOneMatchObliqueRightSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchObliqueRightSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTATGT","AGAAGG","CCGCAA","TCACTA"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueRightSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTGTGT","AGAGGG","CCGCGA","TCACGG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	/***********************   Tests de mutantes con ADN Oblicuo Izquierdo   ***********************/
	@Test
	public void testOneMatchObliqueLeftSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTAC","TTGAGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testOneMatchObliqueLeftSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTAC","TTGAGT","AGAAGG","CAGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertFalse(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueLeftSequenceShouldBeMutant() {
		String[] dna = {"ATGCGG","CAGTAC","TTGAAT","AGAAGG","CAACTA","TACCTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	/***********************   Tests de mutantes con ADN Cruzados   ***********************/
	@Test
	public void testTwoMatchsHorizontalVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATACGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueRightAndHorizontalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","AAAAGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueRightAndVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATACGA","CAATCC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueLeftAndVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATGCAA","CAGTAC","TTGAAT","AGAAAG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsObliqueLeftAndHorizontalSequenceShouldBeMutant() {
		String[] dna = {"TTAAAA","CAGTAC","TTGAGT","AGAAGG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	@Test
	public void testTwoMatchsBootObliquesSequenceShouldBeMutant() {
		String[] dna = {"AAGCGA","CAATAC","TTGAGT","AGAGAG","CCGCTA","TCACTG"};
		boolean isMutant = dnaAnalyzer.isMutant(dna);
		Assert.assertTrue(isMutant);
	}
	
	/***********************   Tests de mutantes con volumen de datos   ***********************/
	@Test
	public void testVolumeData() {
		long time_start = System.currentTimeMillis();
		String[] dna = {"AAGCGA","CAATAC","TTGAGT","AGAGAG","CCGCTA","TCACTG"};
		boolean isMutant = false;
		for (int i = 0; i < 1000000; i++) {
			isMutant = dnaAnalyzer.isMutant(dna);
		}
		long time_end = System.currentTimeMillis();
		System.out.println("Completing Test Volume data: " + ( time_end - time_start ) + " milliseconds");
		Assert.assertTrue(isMutant);
	}
}
