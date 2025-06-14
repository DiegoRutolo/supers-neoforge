package eu.rutolo.minecraft.supers.poderes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.player.Player;

public class Poderes {
	
	public static final Map<String, Superpoder> PODERES = new HashMap<>();
	
	static {
		PODERES.put("bola_de_fuego", new BolaDeFuego());
		PODERES.put("superfuerza", new Superfuerza());
	}

	
	public static Superpoder getPoder(Player player) {
		return PODERES.get("bola_de_fuego");
	}
	
	public static void removePoder(Player player) {
		
	}
	
	public static void setPoder(Player player, Superpoder poder) {
		
	}
	
	public static Superpoder randomPoder() {
		return PODERES.get("bola_de_fuego");
	}
	
	public static void generatePoder(Player player) {
		removePoder(player);
		Superpoder poder = randomPoder();
		setPoder(player, poder);
	}

}
