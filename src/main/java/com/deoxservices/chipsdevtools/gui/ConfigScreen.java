/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipsdevtools.gui;

import com.deoxservices.chipsdevtools.ChipsDevTools;
import com.deoxservices.chipsdevtools.client.ClientProxy;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;

public class ConfigScreen {
    protected Runnable canceller;

    public ConfigScreen(Screen parent) {
        ImmutableMap.Builder<KeyMapping, InputConstants.Key> keyMapBuilder = ImmutableMap.builder();
        keyMapBuilder.put(ClientProxy.COPY_HAND_KEY, ClientProxy.COPY_HAND_KEY.getKey());
        keyMapBuilder.put(ClientProxy.COPY_OFFHAND_KEY, ClientProxy.COPY_OFFHAND_KEY.getKey());
        keyMapBuilder.put(ClientProxy.COPY_HOTBAR_KEY, ClientProxy.COPY_HOTBAR_KEY.getKey());
        keyMapBuilder.put(ClientProxy.COPY_INVENTORY_KEY, ClientProxy.COPY_INVENTORY_KEY.getKey());
        keyMapBuilder.put(ClientProxy.COPY_CONTAINER_KEY, ClientProxy.COPY_CONTAINER_KEY.getKey());
        keyMapBuilder.put(ClientProxy.COPY_ALL_KEY, ClientProxy.COPY_ALL_KEY.getKey());
        var keyMap = keyMapBuilder.build();
        canceller = () -> {
            keyMap.forEach(KeyMapping::setKey);
            ChipsDevTools.getMinecraft().options.save();
        };
    }
}
