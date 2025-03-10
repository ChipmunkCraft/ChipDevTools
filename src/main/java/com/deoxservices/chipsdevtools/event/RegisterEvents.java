/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipsdevtools.event;

import com.deoxservices.chipsdevtools.ItemIDs;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RegisterEvents {

    public static void registerCDTCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {

        final LiteralArgumentBuilder<CommandSourceStack> cdtCommand = Commands.literal("cdt").requires(player -> player.hasPermission(2));

        cdtCommand.then(Commands.literal("hand").executes(commandContext -> ItemIDs.runCDT("hand")))
                  .then(Commands.literal("offhand").executes(commandContext -> ItemIDs.runCDT("offhand")))
                  .then(Commands.literal("hotbar").executes(commandContext -> ItemIDs.runCDT("hotbar")))
                  .then(Commands.literal("inventory").executes(commandContext -> ItemIDs.runCDT("inventory")))
                  .then(Commands.literal("all").executes(commandContext -> ItemIDs.runCDT("all")))
        ;
        dispatcher.register(cdtCommand);
    }
}