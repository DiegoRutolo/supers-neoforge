package eu.rutolo.minecraft.supers.commands;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import eu.rutolo.minecraft.supers.poderes.PoderesUtils;
import eu.rutolo.minecraft.supers.poderes.Superpoder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PowerGetCommand extends PowerAbstractCommand {
	
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		PowerGetCommand command = new PowerGetCommand();
		return Commands.literal("get")
				.executes(ctx -> command.run(ctx, ctx.getSource().getPlayerOrException()))
				.then(Commands.argument(ARG_PLAYER, EntityArgument.player())
						.executes(command::run));
	}

	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		Player player = (Player) EntityArgument.getEntity(context, ARG_PLAYER);
		return run(context, player);
	}
	
	public int run(CommandContext<CommandSourceStack> context, Player player) throws CommandSyntaxException {
		Superpoder poder = PoderesUtils.getPoder(player);
		context.getSource().sendSuccess(
				() -> Component.translatable("supers.commands.power.get.msg", player.getName(), poder.getNombre()), false);
		return 0;
	}
}
