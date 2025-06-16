package eu.rutolo.minecraft.supers.poderes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Clase que define el superpoder. 
 * 
 * Necesita un método activar, y puede que otros para configurar.
 */
public interface Superpoder {
	
	/**
	 * La acción que otorga el superpoder.
	 * 
	 * Para pasivas se ejecutaŕa una sola vez al iniciar la partida.
	 * Para activas se ejecutará cada vez que el jugador pulse el botón.
	 */
	public void activar();
	
	/**
	 * Indica que los poderes otorgan una habilidad pasiva que está siempre activa.
	 * 
	 * @return true para habilidades pasivas
	 */
	public boolean isPasiva();
	
	public void setPlayer(Player player);
	public Player getPlayer();
	
	public PoderesUtils.PoderesType getPoder();
	public String getCodigo();
	public Component getNombre();
	public Component getDescripcion();
	
	/**
	 * Para los poderes que se activan y desactivan.
	 * @return
	 */
	public boolean isEnabled();
	public void toggle();
}
