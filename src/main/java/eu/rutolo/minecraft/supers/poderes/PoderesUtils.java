package eu.rutolo.minecraft.supers.poderes;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import eu.rutolo.minecraft.supers.Supers;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;

public class PoderesUtils {
	
	private static final Logger LOGGER = LogUtils.getLogger();
	
	public enum PoderesType implements StringRepresentable {
		HUMANO_SIN_CLASE,
		BOLA_DE_FUEGO,
		SUPERFUERZA,
		PIEL_DE_ACERO,
		;

		@Override
		public String getSerializedName() {
			return name();
		}
	}
	
	private static final Map<PoderesType, ISuperpoder> PODERES_MAP = new EnumMap<>(PoderesType.class);
	static {
		PODERES_MAP.put(PoderesType.HUMANO_SIN_CLASE, new HumanoSinClase());
		PODERES_MAP.put(PoderesType.BOLA_DE_FUEGO, new BolaDeFuego());
		PODERES_MAP.put(PoderesType.SUPERFUERZA, new Superfuerza());
		PODERES_MAP.put(PoderesType.PIEL_DE_ACERO, new PielDeAcero());		
	}
	
	public static final Codec<ISuperpoder> CODEC = RecordCodecBuilder.create(instance ->
		instance.group(
				Codec.STRING.fieldOf("poderName").forGetter(ISuperpoder::getNombre)
		).apply(instance, PoderesUtils::create)
	);
	
	public static final StringRepresentable.EnumCodec<PoderesType> CODEC_ENUM = StringRepresentable.fromEnum(PoderesType::values);

	public static ISuperpoder poderBase() {
		return PODERES_MAP.get(PoderesType.HUMANO_SIN_CLASE);
	}
	
	public static ISuperpoder create(String poderName) {
		return PODERES_MAP.get(PoderesType.valueOf(poderName.toUpperCase()));
	}
	
	public static ISuperpoder getPoder(PoderesType poderType) {
		return PODERES_MAP.get(poderType);
	}
	
	public static ISuperpoder getPoder(Player player) {
		return player.getData(Supers.SUPERPODER);
	}
	
	public static void removePoder(Player player) {
		setPoder(player, new HumanoSinClase());
	}
	
	public static void setPoder(Player player, String nombrePoder) {
		setPoder(player, PoderesType.valueOf(nombrePoder.toUpperCase()));
	}
	
	public static void setPoder(Player player, PoderesType poder) {
		setPoder(player, PODERES_MAP.get(poder));
	}
	
	public static void setPoder(Player player, ISuperpoder poder) {
		player.setData(Supers.SUPERPODER, poder);
	}

	public static ISuperpoder randomPoder() {
		PoderesType p = PoderesType.values()[new Random().nextInt(PoderesType.values().length-1)+1]; // para no incluir el Humano sin clase
		return PODERES_MAP.get(p);
	}
	
	public static void generatePoder(Player player) {
		LOGGER.info("Generando nuevo superpoder para el jugador {}...", player.getName().getString());
		setPoder(player, randomPoder());
		ISuperpoder poder = player.getData(Supers.SUPERPODER);
		LOGGER.info("Y el poder seleccionado es... ¡{}!", poder.getNombre());
		activarPoder(player);
	}

	/**
	 * Activa los poderes si es necesario, por ejemplo, para la superfuerza otorga los modificacores de los stats.
	 * @param player jugador
	 */
	public static void activarPoder(Player player) {
		if (player.hasData(Supers.SUPERPODER)) {
			ISuperpoder poder = player.getData(Supers.SUPERPODER);
			
			if (poder.getPlayer() == null) {
				poder.setPlayer(player);
			}
			
			if (poder.isPasiva()) {
				poder.activar();
			}
		}
	}
}
