package eu.rutolo.minecraft.supers.poderes;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.Supers;
import net.minecraft.world.entity.player.Player;

public class PoderesUtils {
	
	private static final Logger LOGGER = LogUtils.getLogger();
	
//	public static final Map<String, Superpoder> PODERES = new HashMap<>();
//	
//	static {
//		PODERES.put(HumanoSinClase.NOMBRE, new HumanoSinClase());
//		PODERES.put(BolaDeFuego.NOMBRE, new BolaDeFuego());
//		PODERES.put(Superfuerza.NOMBRE, new Superfuerza());
//	}
	
	public enum Poderes {
		HUMANO_SIN_CLASE,
		BOLA_DE_FUEGO,
		SUPERFUERZA,
		;
	}
	
	public static final Map<Poderes, Superpoder> PODERES_MAP = new EnumMap<>(Poderes.class);
	static {
		PODERES_MAP.put(Poderes.HUMANO_SIN_CLASE, new HumanoSinClase());
		PODERES_MAP.put(Poderes.BOLA_DE_FUEGO, new BolaDeFuego());
		PODERES_MAP.put(Poderes.SUPERFUERZA, new Superfuerza());
	}

	
	public static Superpoder getPoder(Player player) {
		return player.getData(Supers.SUPERPODER);
	}
	
	public static void removePoder(Player player) {
		setPoder(player, new HumanoSinClase());
	}
	
	public static void setPoder(Player player, Superpoder poder) {
		player.setData(Supers.SUPERPODER, poder);
	}

	public static Superpoder randomPoder() {
		Poderes p = Poderes.values()[new Random().nextInt(Poderes.values().length)];
		return PODERES_MAP.get(p);
	}
	
	public static void generatePoder(Player player) {
		LOGGER.info("Generando nuevo superpoder para el jugador {}...", player.getName().getString());
		setPoder(player, randomPoder());
		Superpoder poder = player.getData(Supers.SUPERPODER);
		LOGGER.info("Y el poder seleccionado es... ¡{}!", poder.getNombre());
	}

	/**
	 * Activa los poderes si es necesario, por ejemplo, para la superfuerza otorga los modificacores de los stats.
	 * @param player jugador
	 */
	public static void activarPoder(Player player) {
		if (player.hasData(Supers.SUPERPODER)) {
			Superpoder poder = player.getData(Supers.SUPERPODER);
			
			if (poder.getPlayer() == null) {
				poder.setPlayer(player);
			}
			
			if (poder.isPasiva()) {
				poder.activar();
			}
		}
	}

}
