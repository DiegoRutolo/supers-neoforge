package eu.rutolo.minecraft.supers;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.network.ActivarPoderPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;

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
	public static class ClientEvents {
		@SubscribeEvent
		public static void onClientTick(ClientTickEvent.Post event) {
			while (ACTIVAR_MAPPING.get().consumeClick()) {
				LOGGER.info("Pulsada tecla");
				PacketDistributor.sendToServer(new ActivarPoderPayload(Minecraft.getInstance().player.getId()));
			}		
		}
	}
	
	@EventBusSubscriber(modid = Supers.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {

		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP");
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
		}

		@SubscribeEvent
		public static void registrarTeclas(RegisterKeyMappingsEvent event) {
			LOGGER.info("Registrando teclas");
			event.register(SupersClient.ACTIVAR_MAPPING.get());
		}
	}
}