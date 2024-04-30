/*
 * Created on Fri Apr 19 2024
 *
 * Copyright (c) 2024 DeoX Services
 */
package com.deoxservices.chipdevtools;

import com.deoxservices.chipdevtools.utils.Utils;
import com.deoxservices.chipdevtools.client.ClientProxy;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class ItemIDs {

    private static Player player = ChipDevTools.mc.player;

    /**
     * Runs the /cdt command
     * @param commandContext CommandContext<CommandSourceStack>
     * @param type Command Argument Type
     * @return 1 int
     */
    public static int runCDT(String type) throws CommandSyntaxException {
        if (player != null) {
            switch(type) {
                case "hand" :
                    ItemStack itemStackHand = player.getItemBySlot(EquipmentSlot.MAINHAND);               // Get Main Hand itemStack
                    String itemNameHand = formatDescriptionId(itemStackHand);         // return example: minecraft:oak_planks OR minecraft:apple
                    if (!itemNameHand.equals("minecraft:air")) {             // Make sure to skip minecraft:air
                        Utils.clipboardCopy(itemNameHand);                            // Put formatted DescriptionID in the clipboard
                        Utils.sendMessage("Copied "+type+" to Clipboard!"); // Alert the player the String has been copied
                        Utils.sendMessage(itemNameHand);               // Put the result in chat, so the user knows what the result was
                    }
                    else
                    {
                        Utils.sendMessage("Nothing to copy!"); // Alert player if there was nothing to copy
                    }
                break;
                case "offhand" :
                    ItemStack itemStackOffhand = player.getItemBySlot(EquipmentSlot.OFFHAND);               // Get Offhand itemStack
                    String itemNameOffhand = formatDescriptionId(itemStackOffhand);     // return example: minecraft:oak_planks OR minecraft:apple
                    if (!itemNameOffhand.equals("minecraft:air")) {                // Make sure to skip minecraft:air
                        Utils.clipboardCopy(itemNameOffhand);                           // Put formatted DescriptionID in the clipboard
                        Utils.sendMessage("Copied "+type+" to Clipboard!");   // Alert the player the String has been copied
                        Utils.sendMessage(itemNameOffhand);              // Put the result in chat, so the user knows what the result was
                    }
                    else
                    {
                        Utils.sendMessage("Nothing to copy!"); // Alert player if there was nothing to copy
                    }
                break;
                case "hotbar" :
                    getPlayerInventoryItems(0,8, type); // Get hotbar slots and iterate through each one
                break;

                case "inventory" :
                    getPlayerInventoryItems(9,35, type); // Get upper inventory slots and iterate through each one
                break;

                case "all" :  // Include Offhand item (All encompasses the players FULL inventory (Hotbar, upper inventory and offhand))
                    getPlayerInventoryItems(0,35, type); // Get all inventory slots and iterate through each one TO-DO: include offhand
                break;
            }
            
            return 1;
        } else {
            // There is something wrong with the player.
            Utils.sendMessage("Bad  Sorry! The Princess is in another castle! This should not be possible.");
            return 0;
        }
    }

    /**
     * Formats ItemStack into mod:name eg. minecraft:block
     * @param itemStack ItemStack
     * @return String Formatted item name
     */
    private static String formatDescriptionId(ItemStack itemStack) {
        String itemName = itemStack.getDescriptionId();               // Get descriptionID for the ItemStack
        String pattern = "item\\.|block\\.|entity\\.|tileEntity\\.";  // Pattern for removing the prefix
        itemName = itemName.replaceFirst(pattern, "");    // Remove those prefixes
        itemName = itemName.replaceAll("\\.", ":"); // replace the period with a colon to correctly format string
        return itemName;
    }

    /**
     * Iterates through inventory slots and returns formatted ItemStack into mod:name eg. minecraft:block\nminecraft:apple
     * @param start Int Start Index
     * @param finish Int Finish Index
     * @param type String Command Type
     */
    private static void getPlayerInventoryItems(int start, int finish, String type) {
        @SuppressWarnings("null")
        Inventory pInv = ChipDevTools.mc.player.getInventory();
        String itemNameInv = "";
        int count = 0;
        for (int i =start; i <= finish; i++) {                      // Iterating through inventory slots from start to finish
            String itemName = formatDescriptionId(pInv.getItem(i)); // return example: minecraft:oak_planks OR minecraft:apple
            if (!itemName.equals("minecraft:air")) {       // Make sure to skip minecraft:air
                itemNameInv += itemName + "\n";                     // Add this itemName to the string list
                count++;
            }
        }

        if (count>0) {
            String output = itemNameInv.substring(0,itemNameInv.length()-1); // Remove the last \n from the string
            Utils.clipboardCopy(output);                          // Put formatted DescriptionIDs in the clipboard each separated by new lines
            Utils.sendMessage("Copied "+type+" to Clipboard!"); // Alert the player the String has been copied
            Utils.sendMessage(output);                     // Put the result in chat, so the user knows what the result was
        } else {
            Utils.sendMessage("Nothing to copy!"); // Alert player if there was nothing to copy
        }
    }

    /**
     * Iterates through inventory slots and outputs formatted ItemStack into mod:name into the Clipboard eg. minecraft:block\nminecraft:apple
     */
    public static void getInventoryItems() {
        if (ClientProxy.container != null)
        {
            int size = ClientProxy.container.getItems().size();

            String itemNames = "";
            int count = 0;
            for (int i = 0; i < size; i++) {
                String itemName = formatDescriptionId(ClientProxy.container.getSlot(i).getItem());
                if (!itemName.equals("minecraft:air")) {
                    itemNames += itemName + "\n";
                    count++;
                }
            }

            if (count>0) {
                String output = itemNames.substring(0,itemNames.length()-1); // Remove the last \n from the string
                Utils.clipboardCopy(output);                          // Put formatted DescriptionIDs in the clipboard each separated by new lines
                Utils.sendMessage("Copied items to Clipboard!"); // Alert the player the String has been copied
                Utils.sendMessage(output);                     // Put the result in chat, so the user knows what the result was
            } else {
                Utils.sendMessage("Nothing to copy!"); // Alert player if there was nothing to copy
            }
        } else {
            Utils.logMsg("Container is null","info");
        }
    }
}
