package eu.rutolo.minecraft.supers.commands;

import org.slf4j.Logger;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;

import eu.rutolo.minecraft.supers.poderes.PoderesUtils;
import eu.rutolo.minecraft.supers.poderes.Superpoder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;

public class PowerGenerateCommand extends PowerAbstractCommand {

	private static final String ARG_PLAYER = "player";
	private static final Logger LOGGER = LogUtils.getLogger();
	
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		PowerGenerateCommand command = new PowerGenerateCommand();
		return Commands.literal("generate")
				.requires(ctx -> ctx.hasPermission(2))
				.executes(ctx -> command.runOther(ctx, ctx.getSource().getPlayerOrException()))
				.then(Commands.argument(ARG_PLAYER, EntityArgument.player())
						.executes(command::runSelf));
	}
	
	public int runSelf(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		Player player = (Player) EntityArgument.getEntity(context, ARG_PLAYER);
		return runOther(context, player);
	}
	
	public int runOther(CommandContext<CommandSourceStack> context, Player player) throws CommandSyntaxException {
		Superpoder poder = PoderesUtils.randomPoder();
		StringBuilder msg =new StringBuilder("Jugador: ")
				.append(player.getName().getString())
				.append("; Otorgando poder nuevo.")
				.append(poder.getNombre());
		LOGGER.info(msg.toString());
		return 0;
	}
}
