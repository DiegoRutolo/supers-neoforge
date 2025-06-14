package eu.rutolo.minecraft.supers.poderes;

import com.mojang.logging.LogUtils;

public class HumanoSinClase extends Superpoder {

	public static final String NOMBRE = "humano_sin_clase";

	public HumanoSinClase() {
		super("none");
	}

	@Override
	public void activar() {
		LogUtils.getLogger().info("Nada de nada");
	}

}
