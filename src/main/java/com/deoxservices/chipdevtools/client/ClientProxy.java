/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 Your Company
 */
package com.deoxservices.chipdevtools.client;

import com.deoxservices.chipdevtools.ChipDevReference;
import com.deoxservices.chipdevtools.ItemIDs;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

@Mod.EventBusSubscriber(modid = ChipDevReference.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy {

    public static AbstractContainerMenu container = null;

    public static KeyMapping COPY_ITEMS_KEY = new KeyMapping("Copy Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_C), ChipDevReference.MOD_NAME);

    public static void registerKeyPressed(InputEvent.Key event) {
        if (event.getAction() == InputConstants.PRESS && event.getKey() == getBoundKeyOf(COPY_ITEMS_KEY).getValue()) {
            ItemIDs.getInventoryItems();
        }
    }

    public static InputConstants.Key getBoundKeyOf(KeyMapping keyMapping) {
        return keyMapping.getKey();
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(COPY_ITEMS_KEY);
    }

    public static AbstractContainerMenu registerInventoryOpen(PlayerContainerEvent.Open event) {
        container = event.getContainer();
        return container;
    }
}
