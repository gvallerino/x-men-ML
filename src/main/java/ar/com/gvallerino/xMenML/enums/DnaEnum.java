package ar.com.gvallerino.xMenML.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum DnaEnum {
	
	A("A"),
	T("T"),
	C("C"),
	G("G");
	
	private String typeDna;
	
	DnaEnum(String typeDna) {
		this.typeDna = typeDna;
	}
	
	public String getTypeDna() {
		return typeDna;
	}

	public void setTypeDna(String typeDna) {
		this.typeDna = typeDna;
	}

	public static boolean belongsToDna(String otherDna) {
		
		List<DnaEnum> listDna = Arrays.asList(DnaEnum.values());
		
		for (DnaEnum dna : listDna) {
			if (!dna.getTypeDna().equalsIgnoreCase(otherDna)) {
				return false;
			}
		}
		
		return true;
	}

}
