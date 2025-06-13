package eu.rutolo.minecraft.supers.network;

import eu.rutolo.minecraft.supers.Supers;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ActivarPoderPayload(int entityId) implements CustomPacketPayload {

	public static final String PAYLOAD_PATH = "activar_poder";
	
	public static final CustomPacketPayload.Type<ActivarPoderPayload> TYPE = new CustomPacketPayload.Type<>(
			ResourceLocation.fromNamespaceAndPath(Supers.MODID, PAYLOAD_PATH));
	
	public static final StreamCodec<ByteBuf, ActivarPoderPayload> CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			 ActivarPoderPayload::entityId,
			ActivarPoderPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

}
