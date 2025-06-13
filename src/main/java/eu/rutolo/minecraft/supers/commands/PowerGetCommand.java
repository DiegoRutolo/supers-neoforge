package eu.rutolo.minecraft.supers.commands;

import org.slf4j.Logger;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class PowerGetCommand implements Command<CommandSourceStack> {
	
	private static final Logger LOGGER = LogUtils.getLogger();

	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("get")
//				.requires(x -> x.hasPermission(2))
				.executes(new PowerGetCommand());
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		StringBuilder msg = new StringBuilder("Poder: ???");
		LOGGER.info(msg.toString());
//		context.getSource().sendSystemMessage(Component.literal(msg.toString()));
		context.getSource().sendSuccess(() -> Component.literal(msg.toString()), false);
		
		return 0;
	}

}
