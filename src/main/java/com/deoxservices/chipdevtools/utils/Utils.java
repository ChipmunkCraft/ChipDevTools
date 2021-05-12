package com.deoxservices.chipdevtools.utils;

import com.deoxservices.chipdevtools.ChipDevTools;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.ClipboardHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;

public class Utils {

    public Utils() {
        logMsg("Initialized Utilities","info");
    }

    /**
     * Broadcast message to chat
     * @param commandContext CommandContext<CommandSource>
     * @param msg Message to send
     */
    public static void sendMessage(CommandContext<CommandSource> commandContext, String msg) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
        if (player != null) {
            commandContext.getSource().getServer().getPlayerList().broadcastMessage(new StringTextComponent(msg), ChatType.CHAT, player.getUUID());
        } else {
            commandContext.getSource().getServer().getPlayerList().broadcastMessage(new StringTextComponent(msg), ChatType.SYSTEM, Util.NIL_UUID);
        }
        logMsg("Message Sent","info");
    }
    /**
     * Log Message
     * @param msg Message to log
     * @param type Log Level Type
     */
    public static void logMsg(String msg, String type) {
        String fullMsg = "[" + ChipDevTools.MOD_NAME + "] " + msg;
        switch(type) {
            case "info":
                ChipDevTools.LOGGER.info(fullMsg);
                break;
            case "warn":
                ChipDevTools.LOGGER.warn(fullMsg);
                break;
            case "debug":
                ChipDevTools.LOGGER.debug(fullMsg);
                break;
            case "fatal":
                ChipDevTools.LOGGER.fatal(fullMsg);
                break;
        }
    }

    /**
     * Copy input to clipboard
     * @param str The String to copy to the clipboard
     */
    public static void clipboardCopy(String str) {
        try {
            ClipboardHelper helper = new ClipboardHelper();
            long strLength = str.length();
            helper.setClipboard(strLength, str);
        } catch (Exception e) {
            logMsg("Not Copied to Clipboard! Error: " + e,"info");
        }
    }
}
