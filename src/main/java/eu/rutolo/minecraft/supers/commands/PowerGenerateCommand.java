package eu.rutolo.minecraft.supers.commands;

import org.slf4j.Logger;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.poderes.Superpoder;
import eu.rutolo.minecraft.supers.poderes.PoderesUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PowerGenerateCommand extends PowerAbstractCommand {

	private static final Logger LOGGER = LogUtils.getLogger();
	
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		PowerGenerateCommand command = new PowerGenerateCommand();
		return Commands.literal("generate")
				.requires(ctx -> ctx.hasPermission(2))
				.executes(ctx -> command.run(ctx, ctx.getSource().getPlayerOrException()))
				.then(Commands.argument(ARG_PLAYER, EntityArgument.player())
						.executes(command::run));
	}
	
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		Player player = (Player) EntityArgument.getEntity(context, ARG_PLAYER);
		return run(context, player);
	}
	
	public int run(CommandContext<CommandSourceStack> context, Player player) throws CommandSyntaxException {
		PoderesUtils.generatePoder(player);
		Superpoder poder = PoderesUtils.getPoder(player);
		StringBuilder msg =new StringBuilder("Jugador: ")
				.append(player.getName().getString())
				.append("; Poder: ")
				.append(poder.toString());
		LOGGER.info(msg.toString());
		context.getSource().sendSuccess(() -> Component.literal(msg.toString()), false);
		return 0;
	}
}
