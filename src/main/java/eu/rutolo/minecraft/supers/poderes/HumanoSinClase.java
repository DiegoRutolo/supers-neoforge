package eu.rutolo.minecraft.supers.poderes;

import com.mojang.logging.LogUtils;

public class HumanoSinClase extends Superpoder {

	public HumanoSinClase() {
		super(PoderesUtils.Poderes.HUMANO_SIN_CLASE.toString());
	}

	@Override
	public void activar() {
		LogUtils.getLogger().info("Nada de nada");
	}
	
	@Override
	public boolean isPasiva() {
		return false;
	}

}
