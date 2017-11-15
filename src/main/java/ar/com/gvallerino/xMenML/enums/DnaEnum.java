package ar.com.gvallerino.xMenML.enums;

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
		return DnaEnum.valueOf(otherDna.toUpperCase()) != null;
	}

}
