package ar.com.gvallerino.xMenML.exceptions;

/**
 * Excepcion lanzada si el formato de la informacion es incorrecto.
 * Por ejemplo, una matriz no cuadrada.
 *
 */
public class DnaFormatException extends Exception {
	
	private static final long serialVersionUID = -4962925799723934787L;

	/** Constructor */
	public DnaFormatException() {}
	
	/** Constructor con mensaje de error */
	public DnaFormatException(String message) {
		super(message);
	}

}
