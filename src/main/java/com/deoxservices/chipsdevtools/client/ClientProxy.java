package com.deoxservices.chipsdevtools.client;

import com.deoxservices.chipsdevtools.ChipsDevTools;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;

@EventBusSubscriber(bus = Bus.MOD, modid = ChipsDevTools.MOD_ID, value = Dist.CLIENT)
public class ClientProxy {

    public static AbstractContainerMenu container;
    public static KeyMapping COPY_HAND_KEY = new KeyMapping("Copy Hand Item ID", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_M), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_OFFHAND_KEY = new KeyMapping("Copy Offhand Item ID", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_O), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_HOTBAR_KEY = new KeyMapping("Copy Hotbar Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_H), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_INVENTORY_KEY = new KeyMapping("Copy Inventory Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_I), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_CONTAINER_KEY = new KeyMapping("Copy Container Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_C), ChipsDevTools.MOD_NAME);
    public static KeyMapping COPY_ALL_KEY = new KeyMapping("Copy All Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_A), ChipsDevTools.MOD_NAME);
    public static KeyMapping DISABLE_KEYS = new KeyMapping("Disable Copy Keys", KeyConflictContext.IN_GAME, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_D), ChipsDevTools.MOD_NAME);

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(COPY_HAND_KEY);
        event.register(COPY_OFFHAND_KEY);
        event.register(COPY_HOTBAR_KEY);
        event.register(COPY_INVENTORY_KEY);
        event.register(COPY_CONTAINER_KEY);
        event.register(COPY_ALL_KEY);
        event.register(DISABLE_KEYS);
    }
}
