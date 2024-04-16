package com.deoxservices.chipdevtools.commands;

import com.deoxservices.chipdevtools.utils.Utils;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommandEvent {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> commandDispatcher = event.getDispatcher();

        ItemIDs.register(commandDispatcher);
        Utils.logMsg("Registered Command /cdt","info");
    }
}
