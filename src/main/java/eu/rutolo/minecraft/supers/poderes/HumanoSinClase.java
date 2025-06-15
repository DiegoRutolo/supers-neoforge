package eu.rutolo.minecraft.supers.poderes;

import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.poderes.PoderesUtils.Poderes;
import net.minecraft.world.entity.player.Player;

/**
 * Un humnao comun y corriente, sin clase (ja ja)
 */
public class HumanoSinClase implements ISuperpoder {
	
	private final Poderes poder;
	
	protected Player player;
	
	public HumanoSinClase() {
		this(Poderes.HUMANO_SIN_CLASE);
	}
	
	protected HumanoSinClase(Poderes poder) {
		this.poder = poder;
	}
	
	public void activar() {
		LogUtils.getLogger().info("Nada de nada");
	}
	
	public boolean isPasiva() {
		return false;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
		
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public Poderes getPoder() {
		return this.poder;
	}
	
	public String getNombre() {
		return getPoder().toString();
	}
	
	

}
