package com.neptunecentury.fixedlevels;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * Common methods and fields for config classes
 */
public interface IConfig {
    /**
     * The method used to create a CustomPayload object
     *
     * @return The CustomPayload object to send
     */
    CustomPacketPayload createPayload();
}
