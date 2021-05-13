package com.deoxservices.chipdevtools.commands;

import com.deoxservices.chipdevtools.utils.Utils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;

public class ItemIDs {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> cdt
        = Commands.literal("cdt")
            .requires((commandSource) -> commandSource.hasPermission(1))
            .then(Commands.literal("hand")
                    .executes(commandContext -> runCDT(commandContext,"hand"))
                )
            .then(Commands.literal("hotbar")
                    .executes(commandContext -> runCDT(commandContext,"hotbar"))
                )
            .then(Commands.literal("inventory")
                    .executes(commandContext -> runCDT(commandContext,"inventory"))
                )
            .then(Commands.literal("fullinv")
                    .executes(commandContext -> runCDT(commandContext,"fullinv"))
        );
    dispatcher.register(cdt);
    }

    /**
     * Runs the /cdt command
     * @param commandContext  CommandContext<CommandSource>
     * @param type   Command Arguments
     * @return 1
     */
    static int runCDT(CommandContext<CommandSource> commandContext, String type) throws CommandSyntaxException {

        ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
        if (player != null) {
            switch(type) {
                case "hand" :
                    ItemStack itemStackHand = player.getMainHandItem();       // Get Main Hand itemStack
                    String itemNameHand = formatDescriptionId(itemStackHand); // return example: minecraft:oak_planks OR minecraft:apple
                    Utils.clipboardCopy(itemNameHand);                        // Put formatted DescriptionID in the clipboard
                    Utils.sendMessage(commandContext,"Copied to Clipboard!"); // Alert the player the String has been copied
                    Utils.sendMessage(commandContext,itemNameHand);           // Put the result in chat, so the user knows what the result was
                return 1;

                case "hotbar" :
                    String itemNameHotbar = getInventoryItems(player,0,8);    // Get hotbar slots and iterate through each one
                    Utils.clipboardCopy(itemNameHotbar);                      // Put formatted DescriptionIDs in the clipboard each separated by new lines
                    Utils.sendMessage(commandContext,"Copied to Clipboard!"); // Alert the player the String has been copied
                    Utils.sendMessage(commandContext,itemNameHotbar);         // Put the result in chat, so the user knows what the result was
                return 1;

                case "inventory" :
                    String itemNameInv = getInventoryItems(player,9,35);       // Get upper inventory slots and iterate through each one
                    Utils.clipboardCopy(itemNameInv);                          // Put formatted DescriptionIDs in the clipboard each separated by new line
                    Utils.sendMessage(commandContext,"Copied to Clipboard!");  // Alert the player the String has been copied
                    Utils.sendMessage(commandContext,itemNameInv);             // Put the result in chat, so the user knows what the result was
                return 1;

                case "fullinv" :
                    String itemNameFullInv = getInventoryItems(player,0,35);  // Get upper inventory slots and iterate through each one
                    Utils.clipboardCopy(itemNameFullInv);                     // Put formatted DescriptionIDs in the clipboard each separated by new line
                    Utils.sendMessage(commandContext,"Copied to Clipboard!"); // Alert the player the String has been copied
                    Utils.sendMessage(commandContext,itemNameFullInv);        // Put the result in chat, so the user knows what the result was
                return 1;

            }
        } else {
            // There is something wrong with the ServerPlayerEntity.
            Utils.sendMessage(commandContext,"Bad ServerPlayerEntity, Sorry! The Princess is in another castle! This should not be possible.");
        }

        return 1;
    }

    /**
     * Formats ItemStack into mod:name eg. minecraft:block
     * @param itemStack   ItemStack
     * @TODO: entity and tileEntity are for future use.
     * @return String Formatted item name
     */
    private static String formatDescriptionId(ItemStack itemStack) {
        String itemName = itemStack.getDescriptionId();              // Get descriptionID for the ItemStack
        String pattern = "item\\.|block\\.|entity\\.|tileEntity\\."; // Pattern for removing the prefix
        itemName = itemName.replaceFirst(pattern, "");               // Remove those prefixes
        itemName = itemName.replaceAll("\\.", ":");                  // replace the period with a colon to correctly format string
        return itemName;
    }

    /**
     * Iterates through inventory slots and returns formatted ItemStack into mod:name eg. minecraft:block\nminecraft:apple
     * @param player Player Instance
     * @param start int Start Index
     * @param finish int Finish Index
     * @return String Formatted output
     */
    private static String getInventoryItems(ServerPlayerEntity player, int start, int finish) {

        PlayerInventory pInv = player.inventory;
        String itemNameInv = "";
        for (int i =start; i <= finish; i++) {                      // Iterating through inventory slots from start to finish
            String itemName = formatDescriptionId(pInv.getItem(i)); // return example: minecraft:oak_planks OR minecraft:apple
            if (!itemName.equals("minecraft:air")) {                // Make sure to skip minecraft:air
                itemNameInv += itemName + "\n";                     // Add this itemName to the string list
            }
        }
        String output = itemNameInv.substring(0,itemNameInv.length()-1); // Remove the last \n from the string

        return output;
    }
}
