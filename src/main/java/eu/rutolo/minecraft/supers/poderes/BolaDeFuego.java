package eu.rutolo.minecraft.supers.poderes;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

public class BolaDeFuego extends Superpoder {
	
	private static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void activar() {
		LOGGER.info("BOLA DE FUEGO");
	}

}
