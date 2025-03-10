/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 Your Company
 */
package com.deoxservices.chipsdevtools.client;

import com.deoxservices.chipsdevtools.ChipsDevTools;
import com.deoxservices.chipsdevtools.ItemIDs;
import com.deoxservices.chipsdevtools.utils.Utils;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

@EventBusSubscriber(bus = Bus.MOD, modid = ChipsDevTools.MOD_ID, value = Dist.CLIENT)
public class ClientProxy {

    public static AbstractContainerMenu container;
    public static KeyMapping COPY_HAND_KEY = new KeyMapping("Copy Hand Item ID", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_M), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_OFFHAND_KEY = new KeyMapping("Copy Offhand Item ID", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_O), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_HOTBAR_KEY = new KeyMapping("Copy Hotbar Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_H), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_INVENTORY_KEY = new KeyMapping("Copy Inventory Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_I), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_CONTAINER_KEY = new KeyMapping("Copy Container Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_C), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_ALL_KEY = new KeyMapping("Copy All Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_A), ChipsDevTools.MOD_NAME);

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(COPY_HAND_KEY);
        event.register(COPY_OFFHAND_KEY);
        event.register(COPY_HOTBAR_KEY);
        event.register(COPY_INVENTORY_KEY);
        event.register(COPY_CONTAINER_KEY);
        event.register(COPY_ALL_KEY);
    }
}

@EventBusSubscriber(bus = Bus.GAME, modid = ChipsDevTools.MOD_ID, value = Dist.CLIENT)
class ClientProxyGameEvents {
    @SubscribeEvent
    public static void onScreenKeyPressed(ScreenEvent.KeyPressed.Pre event) throws CommandSyntaxException {
        Utils.logMsg("Key event triggered: " + event.getKeyCode(), "info");
        if (ClientProxy.COPY_HAND_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("hand");
            Utils.logMsg("Triggered hand hotkey", "info");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_OFFHAND_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("offhand");
            event.setCanceled(true);
            Utils.logMsg("Triggered offhand hotkey", "info");
        } else if (ClientProxy.COPY_HOTBAR_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("hotbar");
            event.setCanceled(true);
            Utils.logMsg("Triggered hotbar hotkey", "info");
        } else if (ClientProxy.COPY_INVENTORY_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("inventory");
            event.setCanceled(true);
            Utils.logMsg("Triggered inventory hotkey", "info");
        } else if (ClientProxy.COPY_CONTAINER_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("container");
            event.setCanceled(true);
            Utils.logMsg("Triggered container hotkey", "info");
        } else if (ClientProxy.COPY_ALL_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("all");
            event.setCanceled(true);
            Utils.logMsg("Triggered all hotkey", "info");
        }
    }
    @SubscribeEvent
    public static void registerInventoryOpen(PlayerContainerEvent.Open event) {
        ClientProxy.container = event.getContainer();
        Utils.logMsg("registerInventoryOpen: " + ClientProxy.container, "info");
    }
    @SubscribeEvent
    public static void registerInventoryClose(PlayerContainerEvent.Close event) {
        ClientProxy.container = null;
        Utils.logMsg("registerInventoryClose: " + ClientProxy.container, "info");
    }
}
