package ar.com.gvallerino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.gvallerino.xMenML.exceptions.DnaCodeException;
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
	
	private boolean isMutant(String[] dna) {
		boolean isMutant = false;
		try {
			isMutant = dnaAnalyzer.isMutant(dna);
		} catch (Exception e) {
			isMutant = false;
		}
		return isMutant;
	}
	
	
	/***********************   Tests de validación de datos   ***********************/
	@Test
	public void testRowWithIncompleteInformationShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testColumnWithIncompleteInformationShouldNotBeMutant() {
		String[] dna = {"ATG","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testOneDnaIsIncorrectShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AAAACC","TTUTGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con ADN Horizontal   ***********************/
	@Test
	public void testOneMatchHorizontalSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AATACC","TTATGT","AGAGGG","CCCCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testOneMatchHorizontalSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","AAAAAC","TTATGT","AGAGGG","CTCCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testOneMatchHorizontalSequenceInLimitsShouldNotBeMutant() {
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"ATAAAA","ACATAC","TTGTGT","AGAAGG","CTCCTA","TCAAAA"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testOneMatchHorizontalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AAAAAAAT","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsHorizontalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AAAAAAAA","TGTGTGTG","CTCTCTCT","CGCGCGCG","ATATATAT","ACACACAC","CACACACA","GAGAGAGA"};
		Assert.assertTrue(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con ADN Vertical   ***********************/
	@Test
	public void testTwoMatchsVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGG","AATTCC","ATATGC","AGAAGC","CCCCTC","TCACTC"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testOneMatchVerticalSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","ATTAAC","TTATGT","ATAAGG","CTCCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testOneMatchVerticalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTT"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceInLimitsShouldBeMutant() {
		String[] dna = {"CTGCGA","CTTAAT","ACATGC","ATAAGC","ATCCTC","ACACTC"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsVerticalSequenceCorrelativesShouldBeMutant() {
		String[] dna = {"AGACATAT","AGTGTGTG","ATCTCTCT","AGCGCGCG","ATATATAT","ACACACAC","AACACACA","AAGAGAGA"};
		Assert.assertTrue(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con ADN Oblicuo Derecho   ***********************/
	@Test
	public void testOneMatchObliqueRightSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testOneMatchObliqueRightSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTATGT","AGAAGG","CCGCAA","TCACTA"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueRightSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","TTGTGT","AGAGGG","CCGCGA","TCACGG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con ADN Oblicuo Izquierdo   ***********************/
	@Test
	public void testOneMatchObliqueLeftSequenceShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTAC","TTGAGT","AGAAGG","CCGCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testOneMatchObliqueLeftSequenceFiveDnaShouldNotBeMutant() {
		String[] dna = {"ATGCGA","CAGTAC","TTGAGT","AGAAGG","CAGCTA","TCACTG"};
		Assert.assertFalse(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueLeftSequenceShouldBeMutant() {
		String[] dna = {"ATGCGG","CAGTAC","TTGAAT","AGAAGG","CAACTA","TACCTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con ADN Cruzados   ***********************/
	@Test
	public void testTwoMatchsHorizontalVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATACGA","AAAACC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueRightAndHorizontalSequenceShouldBeMutant() {
		String[] dna = {"ATGCGA","CAGTCC","AAAAGT","AGAAGG","CCGCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueRightAndVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATACGA","CAATCC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueLeftAndVerticalSequenceShouldBeMutant() {
		String[] dna = {"ATGCAA","CAGTAC","TTGAAT","AGAAAG","CCGCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsObliqueLeftAndHorizontalSequenceShouldBeMutant() {
		String[] dna = {"TTAAAA","CAGTAC","TTGAGT","AGAAGG","CCGCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	@Test
	public void testTwoMatchsBootObliquesSequenceShouldBeMutant() {
		String[] dna = {"AAGCGA","CAATAC","TTGAGT","AGAGAG","CCGCTA","TCACTG"};
		Assert.assertTrue(isMutant(dna));
	}
	
	/***********************   Tests de mutantes con volumen de datos   ***********************/
	@Test
	public void testVolumeData() {
		long time_start = System.currentTimeMillis();
		String[] dna = {"AAGCGA","CAATAC","TTGAGT","AGAGAG","CCGCTA","TCACTG"};
		boolean isMutant = false;
		for (int i = 0; i < 1000000; i++) {
			isMutant = isMutant(dna);
		}
		long time_end = System.currentTimeMillis();
		System.out.println("Completing Test Volume data: " + ( time_end - time_start ) + " milliseconds");
		Assert.assertTrue(isMutant);
	}
}
