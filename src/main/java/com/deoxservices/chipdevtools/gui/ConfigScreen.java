/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipdevtools.gui;

import com.deoxservices.chipdevtools.ChipDevTools;
import com.deoxservices.chipdevtools.client.ClientProxy;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;

public class ConfigScreen {

    protected Runnable canceller;

    public ConfigScreen(Screen parent) {

        ImmutableMap.Builder<KeyMapping, InputConstants.Key> keyMapBuilder = ImmutableMap.builder();
        for (KeyMapping keyMapping : ChipDevTools.mc.options.keyMappings) {
            if (ClientProxy.COPY_ITEMS_KEY.getCategory().equals(keyMapping.getCategory())) {
                keyMapBuilder.put(keyMapping, ClientProxy.getBoundKeyOf(keyMapping));
            }
        }
        var keyMap = keyMapBuilder.build();
        canceller = () -> {
            keyMap.forEach(KeyMapping::setKey);
            ChipDevTools.mc.options.save();
        };

    }
}
