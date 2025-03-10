package com.deoxservices.chipsdevtools.client;

import com.deoxservices.chipsdevtools.ChipsDevTools;
import com.deoxservices.chipsdevtools.ItemIDs;
import com.deoxservices.chipsdevtools.utils.Utils;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;

@EventBusSubscriber(bus = Bus.GAME, modid = ChipsDevTools.MOD_ID, value = Dist.CLIENT)
public class ClientProxyGameEvents {
    private static boolean copyKeysDisabled = true;
    @SubscribeEvent
    public static void registerKeyPressed(InputEvent.Key event) {
        if (ClientProxy.DISABLE_KEYS.consumeClick()) {
            toggleCopyKeys();
        }
    }
    @SubscribeEvent
    public static void onScreenKeyPressed(ScreenEvent.KeyPressed.Pre event) throws CommandSyntaxException {
        if (copyKeysDisabled==true) return;
        if (ClientProxy.COPY_HAND_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("hand");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_OFFHAND_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("offhand");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_HOTBAR_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("hotbar");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_INVENTORY_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("inventory");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_CONTAINER_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("container");
            event.setCanceled(true);
        } else if (ClientProxy.COPY_ALL_KEY.matches(event.getKeyCode(), event.getScanCode())) {
            ItemIDs.runCDT("all");
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void registerInventoryOpen(PlayerContainerEvent.Open event) {
        ClientProxy.container = event.getContainer();
    }
    @SubscribeEvent
    public static void registerInventoryClose(PlayerContainerEvent.Close event) {
        ClientProxy.container = null;
    }
    public static int toggleCopyKeys() {
        if (copyKeysDisabled==true) {
            copyKeysDisabled=false;
            Utils.sendMessage("Copy keys enabled!");
        } else if (copyKeysDisabled==false) {
            copyKeysDisabled=true;
            Utils.sendMessage("Copy keys disabled!");
        }
        return 1;
    }
}