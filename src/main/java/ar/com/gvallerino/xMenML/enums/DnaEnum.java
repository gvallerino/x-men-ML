package ar.com.gvallerino.xMenML.enums;

/**
 * Enum de letras que representan los ADNs validos.
 */
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

	/**
	 * Verifica si un ADN simple (Letra) corresponde con los ADNs validos.
	 * @return boolean
	 */
	public static boolean belongsToDna(String dna) {
		return DnaEnum.valueOf(dna.toUpperCase()) != null;
	}

}
