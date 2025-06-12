package eu.rutolo.minecraft.supers.poderes;

/**
 * Clase que define el superpoder. 
 * 
 * Necesita un método activar, y puede que otros para configurar.
 */
public abstract class Superpoder {
	
	private final String nombre;
	
	public Superpoder(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void activar();
	
	public String getNombre() {
		return this.nombre;
	}
}
