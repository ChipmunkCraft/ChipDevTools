/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipdevtools.utils;

import com.deoxservices.chipdevtools.ChipDevTools;
import com.mojang.blaze3d.platform.ClipboardManager;
import net.minecraft.network.chat.Component;

public class Utils {

    /**
     * Send message to chat
     * @param player Player
     * @param msg Message to send
     */
    public static void sendMessage(String msg) {
        if (ChipDevTools.mc.player != null) {
            ChipDevTools.mc.player.displayClientMessage(Component.literal(msg), false);
        }
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
            ClipboardManager manager = new ClipboardManager();
            long strLength = str.length();
            manager.setClipboard(strLength, str);
            
        } catch (Exception e) {
            logMsg("Not Copied to Clipboard! Error: " + e,"warn");
        }
    }
}
