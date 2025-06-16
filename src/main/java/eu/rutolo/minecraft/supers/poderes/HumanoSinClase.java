package eu.rutolo.minecraft.supers.poderes;

import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.poderes.PoderesUtils.PoderesType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Un humnao comun y corriente, sin clase (ja ja)
 */
public class HumanoSinClase implements Superpoder {
	
	private final PoderesType poder;
	private boolean enabled;
	
	protected Player player;
	
	public HumanoSinClase() {
		this(PoderesType.HUMANO_SIN_CLASE);
	}
	
	protected HumanoSinClase(PoderesType poder) {
		this.poder = poder;
		this.enabled = false;
	}
	
	protected HumanoSinClase(PoderesType poder, boolean enabled) {
		this.poder = poder;
		this.enabled = enabled;
	}
	
	public void activar() {
		LogUtils.getLogger().info("Nada de nada");
	}
	
	public boolean isPasiva() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@Override
	public void toggle() {
		this.enabled = !this.enabled;
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
	public PoderesType getPoder() {
		return this.poder;
	}
	
	public String getCodigo() {
		return getPoder().toString();
	}
	
	public Component getNombre() {
		return Component.translatable("supers.poder." + getCodigo().toLowerCase() + ".name");
	}
	
	public Component getDescripcion() {
		return Component.translatable("supers.poder." + getCodigo().toLowerCase() + ".descripcion");
	}

}
