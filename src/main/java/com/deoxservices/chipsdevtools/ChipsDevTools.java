/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipsdevtools;

import com.deoxservices.chipsdevtools.event.RegisterEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * ChipsDevTools, because why make development harder?
 *
 * @author Chipmunk
 */
@Mod("chipsdevtools")
public class ChipsDevTools {
    public static final String MOD_ID     = "chipsdevtools";
    public static final String MOD_PREFIX = MOD_ID + ":";
    public static final String MOD_NAME   = "Chips Dev Tools";
    public static final Logger LOGGER     = LogManager.getLogger(MOD_ID);

    public ChipsDevTools() {
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
    }

    public static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    private void registerCommands(RegisterCommandsEvent event) {
        RegisterEvents.registerCDTCommand(event.getDispatcher(), event.getBuildContext());
    }
}