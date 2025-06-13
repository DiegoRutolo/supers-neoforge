package eu.rutolo.minecraft.supers;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.commands.PowerGetCommand;
import eu.rutolo.minecraft.supers.network.ActivarPoderPayload;
import eu.rutolo.minecraft.supers.network.ActivarPoderServerHandler;
import net.minecraft.commands.Commands;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


@Mod(Supers.MODID)
public class Supers {
	public static final String MODID = "supers";
	private static final Logger LOGGER = LogUtils.getLogger();

	// Registros
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, MODID);
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);


	// The constructor for the mod class is the first code that is run when your mod
	// is loaded.
	// FML will recognize some parameter types like IEventBus or ModContainer and
	// pass them in automatically.
	public Supers(IEventBus modEventBus, ModContainer modContainer) {
		// Register the commonSetup method for modloading
		modEventBus.addListener(this::commonSetup);

		// Register the Deferred Register to the mod event bus so blocks get registered
		BLOCKS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so items get registered
		ITEMS.register(modEventBus);
		// Register the Deferred Register to the mod event bus so tabs get registered
		CREATIVE_MODE_TABS.register(modEventBus);
		ATTACHMENT_TYPES.register(modEventBus);

		// Register ourselves for server and other game events we are interested in.
		// Note that this is necessary if and only if we want *this* class (Supers) to
		// respond directly to events.
		// Do not add this line if there are no @SubscribeEvent-annotated functions in
		// this class, like onServerStarting() below.
		NeoForge.EVENT_BUS.register(this);

		// Register the item to a creative tab
		modEventBus.addListener(this::addCreative);

		// Register our mod's ModConfigSpec so that FML can create and load the config
		// file for us
		modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP");

		if (Config.LOG_DIRT_BLOCK.getAsBoolean())
			LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

		LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

		Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
	}

	// Add the example block item to the building blocks tab
	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		LOGGER.info("Añadir a la tab");
//		if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//			event.accept(EXAMPLE_BLOCK_ITEM);
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
	
	@SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent e) {
		e.getDispatcher().register(Commands.literal(MODID)
				.then(Commands.literal("power")
					.then(PowerGetCommand.register())));
	}
	
	@EventBusSubscriber(modid = Supers.MODID, bus = EventBusSubscriber.Bus.MOD)
	public static class ModEvents {
		
		@SubscribeEvent
		public static void register(final RegisterPayloadHandlersEvent event) {
		    // Sets the current network version
		    final PayloadRegistrar registrar = event.registrar("1");
			registrar.playToServer(ActivarPoderPayload.TYPE, ActivarPoderPayload.CODEC,
					ActivarPoderServerHandler::handle);
		}
	}

}
