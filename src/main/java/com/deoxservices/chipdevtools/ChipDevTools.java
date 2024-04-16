package com.deoxservices.chipdevtools;

import com.deoxservices.chipdevtools.client.ClientProxy;
import com.deoxservices.chipdevtools.commands.RegisterCommandEvent;
import com.deoxservices.chipdevtools.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

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
        Utils.logMsg("ChipDev Loading...","info");
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(RegisterCommandEvent.class);
        Utils.logMsg("Registered Commands Event","info");
        MinecraftForge.EVENT_BUS.addListener(ClientProxy::onInventoryOpen);
        Utils.logMsg("Registered Inventory Opened Event","info");
        ClientProxy.init();
    }
}