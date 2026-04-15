package com.neptunecentury.fixedlevels;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * An implementation of the CustomPayload to store config options to encode and send to the client
 *
 * @param curveMode
 * @param baseXPForOneLevel
 * @param curveModeMultiplier
 */
public record ConfigPayload(boolean useCustomExpLevels, boolean curveMode, int baseXPForOneLevel,
                            int curveModeMultiplier, boolean useExpCap, int maxExpForNextLevel) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ConfigPayload> ID = new CustomPacketPayload.Type<>(FixedLevels.CONFIG_PACKET_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ConfigPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, ConfigPayload::useCustomExpLevels,
            ByteBufCodecs.BOOL, ConfigPayload::curveMode,
            ByteBufCodecs.INT, ConfigPayload::baseXPForOneLevel,
            ByteBufCodecs.INT, ConfigPayload::curveModeMultiplier,
            ByteBufCodecs.BOOL, ConfigPayload::useExpCap,
            ByteBufCodecs.INT, ConfigPayload::maxExpForNextLevel,
            ConfigPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}