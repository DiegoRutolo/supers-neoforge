package eu.rutolo.minecraft.supers.poderes;

import net.minecraft.world.entity.player.Player;

/**
 * Clase que define el superpoder. 
 * 
 * Necesita un método activar, y puede que otros para configurar.
 */
public abstract class Superpoder {
	
	private final String nombre;
	/**
	 * La entidad que utiliza el superpoder, normalmente un jugador.
	 */
	protected Player player;
	
	public Superpoder(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void activar();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Indica que los poderes otorgan una habilidad pasiva que está siempre activa.
	 * 
	 * @return true para habilidades pasivas
	 */
	public abstract boolean isPasiva();
}
