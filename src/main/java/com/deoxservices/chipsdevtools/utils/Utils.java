package com.deoxservices.chipsdevtools.utils;

import com.deoxservices.chipsdevtools.ChipsDevTools;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Utils {

    /**
     * Send message to chat
     * @param player Player
     * @param msg Message to send
     */
    public static void sendMessage(String msg) {
        Player player = ChipsDevTools.getMinecraft().player;
        if (player==null) return;
        player.displayClientMessage(Component.literal(msg), false);
    }

    /**
     * Log Message
     * @param msg Message to log
     * @param type Log Level Type
     */
    public static void logMsg(String msg, String level) {
        String fullMsg = "[" + ChipsDevTools.MOD_NAME + "] " + msg;
        switch(level.toLowerCase()) {
            case "info" -> ChipsDevTools.LOGGER.info(fullMsg);
            case "warn" -> ChipsDevTools.LOGGER.warn(fullMsg);
            case "debug" -> ChipsDevTools.LOGGER.debug(fullMsg);
            case "fatal" -> ChipsDevTools.LOGGER.fatal(fullMsg);
            default -> ChipsDevTools.LOGGER.info(fullMsg);
        }
    }

    /**
     * Copy input to clipboard
     * @param str The String to copy to the clipboard
     */
    public static void clipboardCopy(String str) {
        try {
            ChipsDevTools.getMinecraft().keyboardHandler.setClipboard(str);
        } catch (Exception e) {
            logMsg("Not Copied to Clipboard! Error: " + e,"warn");
        }
    }
}
