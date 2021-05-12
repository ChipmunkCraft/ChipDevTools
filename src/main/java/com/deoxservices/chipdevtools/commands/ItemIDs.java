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
                    ItemStack itemStackHand = player.getMainHandItem();
                    String itemNameHand = formatDescriptionId(itemStackHand);//  return example: minecraft:oak_planks OR minecraft:apple
                    Utils.clipboardCopy(itemNameHand);
                    Utils.sendMessage(commandContext,"Copied to Clipboard!");
                    Utils.sendMessage(commandContext,itemNameHand);
                return 1;

                case "hotbar" :
                    String itemNameHotbar = getInventoryItems(player,0,8);
                    Utils.clipboardCopy(itemNameHotbar);
                    Utils.sendMessage(commandContext,"Copied to Clipboard!");
                    Utils.sendMessage(commandContext,itemNameHotbar);
                return 1;

                case "inventory" :
                    String itemNameInv = getInventoryItems(player,9,35);
                    Utils.clipboardCopy(itemNameInv);
                    Utils.sendMessage(commandContext,"Copied to Clipboard!");
                    Utils.sendMessage(commandContext,itemNameInv);
                return 1;

                case "fullinv" :
                    String itemNameFullInv = getInventoryItems(player,0,35);
                    Utils.clipboardCopy(itemNameFullInv);
                    Utils.sendMessage(commandContext,"Copied to Clipboard!");
                    Utils.sendMessage(commandContext,itemNameFullInv);
                return 1;

            }
        } else {
            Utils.sendMessage(commandContext,"Sorry! The Princess is in another castle! Can't find command owner. This should not be possible.");
        }

        return 1;
    }

    /**
     * Formats ItemStack into mod:name eg. minecraft:block
     * @param itemStack   ItemStack
     * @return String Formatted item name
     */
    private static String formatDescriptionId(ItemStack itemStack) {
        String itemName = itemStack.getDescriptionId();
        String pattern = "item\\.|block\\.|entity\\.|tileEntity\\.";
        itemName = itemName.replaceFirst(pattern, "");
        itemName = itemName.replaceAll("\\.", ":");
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
        for (int i =start; i <= finish; i++) {
            String itemName = formatDescriptionId(pInv.getItem(i)); //  return example: minecraft:oak_planks OR minecraft:apple
            if (!itemName.equals("minecraft:air")) {
                itemNameInv += itemName + "\n";
                Utils.logMsg(itemNameInv,"info");
            }
        }
        String output = itemNameInv.substring(0,itemNameInv.length()-1);

        return output;
    }
}
