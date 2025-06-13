package eu.rutolo.minecraft.supers.network;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.poderes.BolaDeFuego;
import eu.rutolo.minecraft.supers.poderes.Superpoder;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ActivarPoderServerHandler {
	
	private static final Logger LOGGER = LogUtils.getLogger();

	public static void handle(final ActivarPoderPayload data, final IPayloadContext context) {
		LOGGER.info("Recibido paquete");
        context.enqueueWork(() -> activarPoder(context.player()));
    }
	
	private static void activarPoder(LivingEntity player) {
		Superpoder poder = new BolaDeFuego();
		poder.setPlayer(player);
		poder.activar();
	}
}
