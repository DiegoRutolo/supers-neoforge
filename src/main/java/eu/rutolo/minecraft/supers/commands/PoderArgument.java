package eu.rutolo.minecraft.supers.commands;

import com.mojang.brigadier.context.CommandContext;

import eu.rutolo.minecraft.supers.poderes.PoderesUtils;
import eu.rutolo.minecraft.supers.poderes.PoderesUtils.PoderesType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class PoderArgument extends StringRepresentableArgument<PoderesType> {

	public PoderArgument() {
		super(PoderesUtils.CODEC_ENUM, PoderesType::values);
	}
	
	public static PoderArgument poderes() {
		return new PoderArgument();
	}
	
	public static PoderesType getPoderType(CommandContext<CommandSourceStack> context, String argument) {
        return context.getArgument(argument, PoderesType.class);
    }
}
