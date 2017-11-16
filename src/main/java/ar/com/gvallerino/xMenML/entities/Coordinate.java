package ar.com.gvallerino.xMenML.entities;

/**
 * Clase que representa una cordenada en la matriz de ADN.
 */
public class Coordinate {
	
	/** Fila de la matriz*/
	private int x;
	
	/** Columna de la matriz*/
	private int y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Se define el metodo equals para conocer si una coordenada es igual a otra.
	 * @param other Coordinate
	 * @return boolean
	 */
	public boolean equals(Coordinate other) {
		return x == other.getX() && y == other.getY();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
