package ar.com.gvallerino.xMenML.exceptions;

/**
 * Excepcion lanzada si un ADN simple (Letra) no es correcto.
 *
 */
public class DnaCodeException extends Exception {

	private static final long serialVersionUID = -7309698603496769025L;

	/** Constructor */
	public DnaCodeException() {}
	
	/** Constructor con mensaje de error */
	public DnaCodeException(String message) {
		super(message);
	}
}
