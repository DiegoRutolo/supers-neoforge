package eu.rutolo.minecraft.supers.poderes;

import net.minecraft.world.entity.LivingEntity;

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
	protected LivingEntity player;
	
	public Superpoder(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract void activar();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setPlayer(LivingEntity player) {
		this.player = player;
	}
}
