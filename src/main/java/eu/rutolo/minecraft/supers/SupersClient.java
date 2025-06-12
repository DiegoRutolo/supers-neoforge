package eu.rutolo.minecraft.supers;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = Supers.MODID, dist = Dist.CLIENT)
public class SupersClient {
	private static final Logger LOGGER = LogUtils.getLogger();
	
	// Key mapping is lazily initialized so it doesn't exist until it is registered
	public static final Lazy<KeyMapping> ACTIVAR_MAPPING = Lazy.of(() -> new KeyMapping("supers.tecla.activar.nombre",
			KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "supers.tecla.categoria"));

	public SupersClient(ModContainer container) {
		// Allows NeoForge to create a config screen for this mod's configs.
		// The config screen is accessed by going to the Mods screen > clicking on your
		// mod > clicking on config.
		// Do not forget to add translations for your config options to the en_us.json
		// file.
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
	}
	
	@EventBusSubscriber(modid = Supers.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
	public static class ClienteEvents {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			while (ACTIVAR_MAPPING.get().consumeClick()) {
				LOGGER.info("Pulsada tecla");
			}		
	}
	
	}

}