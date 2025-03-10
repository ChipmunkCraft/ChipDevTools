/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipsdevtools;

import com.deoxservices.chipsdevtools.utils.Utils;
import com.deoxservices.chipsdevtools.client.ClientProxy;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class ItemIDs {
    private static int count = 0;

    /**
     * Runs the /cdt command
     * @param commandContext CommandContext<CommandSourceStack>
     * @param type Command Argument Type
     * @return 1 int
     */
    public static int runCDT(String type) throws CommandSyntaxException {
        Player player = ChipsDevTools.getMinecraft().player;
        if (player==null) {
            Utils.sendMessage("Player not found!");
            return 0;
        }
        Utils.logMsg("Triggered " + type + " copy", "info");
        StringBuilder itemIDs = new StringBuilder();
        count = 0;
        switch(type) {
            case "hand":
                itemIDs.append(copyHandItem());
            break;
            case "offhand":
                itemIDs.append(copyOffHandItem());
            break;
            case "hotbar":
                itemIDs.append(copyInventoryItems(0,8));
            break;
            case "inventory":
                itemIDs.append(copyInventoryItems(9,35));
            break;
            case "container":
                itemIDs.append(copyContainerItems());
            break;
            case "all":
                itemIDs.append(copyContainerItems());
                itemIDs.append(copyInventoryItems(0,35));
            break;
        }
        copyItemsToClipboard(itemIDs, type);
        return 1;
    }

    /**
     * Iterates through inventory slots (container and player) and outputs formatted ItemStack into mod:name into the Clipboard eg. minecraft:block\nminecraft:apple
     */
    public static StringBuilder copyContainerItems() {
        Minecraft mc = ChipsDevTools.getMinecraft();
        StringBuilder itemNames = new StringBuilder();
        if (mc.level != null && mc.player != null)
        {
            if (ClientProxy.container != null)
            {
                int size = ClientProxy.container.getItems().size();
                for (int i = 0; i < size; i++) {
                    String itemName = formatDescriptionId(ClientProxy.container.getSlot(i).getItem());
                    if (!itemName.equals("minecraft:air")) {
                        itemNames.append(itemName).append("\n");
                    }
                }
                itemNames.append(copyInventoryItems(0,35));
            } else {
                Utils.logMsg("Container is null","info");
            }
        }
        return itemNames;
    }

    /**
     * Iterates through player inventory slots and returns formatted ItemStack into mod:name eg. minecraft:block\nminecraft:apple
     * @param start Int Start Index
     * @param finish Int Finish Index
     */
    private static StringBuilder copyInventoryItems(int start, int finish) {
        Player player = ChipsDevTools.getMinecraft().player;
        StringBuilder itemNames = new StringBuilder();
        if (player!=null) {
            Inventory pInv = player.getInventory();
            for (int i =start; i <= finish; i++) {
                String itemName = formatDescriptionId(pInv.getItem(i));
                if (!itemName.equals("minecraft:air")) {
                    itemNames.append(itemName).append("\n");
                    count++;
                }
            }
        }
        return itemNames;
    }

    /**
     * Gets player hand item id and returns formatted ItemStack into mod:name eg. minecraft:block
     */
    private static StringBuilder copyHandItem() {
        Player player = ChipsDevTools.getMinecraft().player;
        StringBuilder itemNames = new StringBuilder();
        if (player!=null) {
            ItemStack itemStackHand = player.getItemBySlot(EquipmentSlot.MAINHAND);
            String itemNameHands = formatDescriptionId(itemStackHand);
            if (!itemNameHands.equals("minecraft:air")) itemNames.append(itemNameHands).append("\n");
            count++;
        }
        return itemNames;
    }

    /**
     * Gets player offhand item id and returns formatted ItemStack into mod:name eg. minecraft:block
     */
    private static StringBuilder copyOffHandItem() {
        Player player = ChipsDevTools.getMinecraft().player;
        StringBuilder itemNames = new StringBuilder();
        if (player!=null) {
            ItemStack itemStackOffhand = player.getItemBySlot(EquipmentSlot.OFFHAND);
            String itemNameOffhand = formatDescriptionId(itemStackOffhand);
            if (!itemNameOffhand.equals("minecraft:air")) itemNames.append(itemNameOffhand).append("\n");
            count++;
        }
        return itemNames;
    }

    /**
     * Copies itemNames to clipboard
     * @param StringBuilder itemNames
     * @param String type
     */
    public static void copyItemsToClipboard(StringBuilder itemNames, String type) {
        if (itemNames.length()>0 && count>0) {
            String output = itemNames.substring(0,itemNames.length()-1);
            Utils.clipboardCopy(output);
            Utils.sendMessage(type+" Items to Clipboard!");
            Utils.sendMessage(output);
        } else {
            Utils.sendMessage("Nothing to copy! (No Items)");
        }
    }

    /**
     * Formats ItemStack into mod:name eg. minecraft:block
     * @param itemStack ItemStack
     * @return String Formatted itemStack
     */
    private static String formatDescriptionId(ItemStack itemStack) {
        return itemStack.getDescriptionId().replaceFirst("item\\.|block\\.|entity\\.|tileEntity\\.", "").replaceAll("\\.", ":");
    }

}
