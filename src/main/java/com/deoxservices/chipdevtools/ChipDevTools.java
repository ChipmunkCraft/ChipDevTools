/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipdevtools;

import com.deoxservices.chipdevtools.client.ClientProxy;
import com.deoxservices.chipdevtools.event.RegisterEvents;
import com.deoxservices.chipdevtools.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * ChipDevTools, because why make development harder?
 *
 * @author Chipmunk
 */

@Mod(ChipDevReference.MOD_ID)
public class ChipDevTools {
    public static final Logger LOGGER     = LogManager.getLogger(ChipDevReference.MOD_ID);
    public static final String MOD_ID     = ChipDevReference.MOD_ID;
    public static final String MOD_PREFIX = ChipDevReference.MOD_PREFIX;
    public static final String MOD_NAME   = ChipDevReference.MOD_NAME;
    public static Minecraft mc = Minecraft.getInstance();

    public ChipDevTools() {
        Utils.logMsg(ChipDevReference.MOD_NAME + " Loading...","info");
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        NeoForge.EVENT_BUS.addListener(ClientProxy::registerInventoryOpen);
        NeoForge.EVENT_BUS.addListener(ClientProxy::registerKeyPressed);
    }

    private void registerCommands(RegisterCommandsEvent event) {
        RegisterEvents.registerCDTCommand(event.getDispatcher(), event.getBuildContext());
    }
}