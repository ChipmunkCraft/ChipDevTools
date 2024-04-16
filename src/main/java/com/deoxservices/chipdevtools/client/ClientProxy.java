package com.deoxservices.chipdevtools.client;

import com.deoxservices.chipdevtools.ChipDevReference;
import com.deoxservices.chipdevtools.commands.ItemIDs;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@OnlyIn(Dist.CLIENT)
public final class ClientProxy {

    public static AbstractContainerMenu container = null;

    public static KeyMapping COPY_ITEMS_KEY = new KeyMapping("Copy Item IDs", KeyConflictContext.GUI, KeyModifier.CONTROL, InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_C), ChipDevReference.MOD_NAME);

    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(ClientProxy::onKeyPressed);
    }

    private static void onKeyPressed(InputEvent.Key event) {
        if (event.getAction() == InputConstants.PRESS && event.getKey() == getBoundKeyOf(COPY_ITEMS_KEY).getValue()) {
            ItemIDs.onKeyPressed(event.getAction());
        }
    }

    public static InputConstants.Key getBoundKeyOf(KeyMapping keyMapping) {
        return keyMapping.getKey();
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(COPY_ITEMS_KEY);
    }

    public static AbstractContainerMenu onInventoryOpen(PlayerContainerEvent.Open event) {
        container = event.getContainer();
        return container;
    }
}
