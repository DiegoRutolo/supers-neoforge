package eu.rutolo.minecraft.supers.poderes;

import java.util.function.Consumer;

import eu.rutolo.minecraft.supers.Supers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BolaDeFuegoTest {
	
	public static final DeferredRegister<Consumer<GameTestHelper>> TEST_FUNCTION = DeferredRegister
			.create(BuiltInRegistries.TEST_FUNCTION, Supers.MODID);
	
	public static final DeferredHolder<Consumer<GameTestHelper>, Consumer<GameTestHelper>> BOLA_DE_FUEGO_TEST_FUNCTION = TEST_FUNCTION
			.register("bolaDeFuegoTest", () -> BolaDeFuegoTest::bolaDeFuegoTest);
	
	public static void bolaDeFuegoTest(GameTestHelper helper) {
		System.out.println("Hola test!");
	}

}
