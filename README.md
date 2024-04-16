# ChipDevTools
#### Chipmunks' Development Tools for Minecraft/Forge Mod/Pack Development

##### Features:
* Adds /cdt command
  * ***Arguments:***
    * *hand*
    * *offhand*
    * *hotbar*
    * *inventory*
    * *all*

The ***/cdt hand*** command for example will get a formatted Description ID of the item in your hand and automatically copy it to your clipboard as long as you are holding an item.
Let's say you are holding cobblestone.  Running ***/cdt hand*** will put ***minecraft:cobblestone*** into your clipboard.

The ***/cdt offhand*** command will accomplish the same but for the item in your offhand

Or, you run ***/cdt hotbar***; will iterate through each slot in the hotbar, find the items in the hotbar and will put each Description ID in your clipboard.
***Example:*** You have cobblestone, an apple and furnace in your hotbar.  This will put the following into your Clipboard *(each item is on its' own line)*:
>minecraft:cobblestone
minecraft:apple
minecraft:furnace

For the argument ***inventory***; it will get each item in the players upper inventory.
Argument ***all*** will do both the hotbar and upper player inventory.

*Ctrl+C* can be used within a chest or similar inventory menu to copy all item IDs of both the player inventory and the chest.

This is useful for modders/modpack makers who may need to do common tasks without having to manually enter each item description id.
The true power here is when you are using an IDE/text editor that supports multi-line editing.

More features will be implemented in the future; however, this is all that is necessary at the moment.

Please feel free to put in a feature request through the issue tracker (*feature suggestions are not guaranteed to be implemented*).

***NOTE:*** We are porting NeoForge

[ [Feature Request](https://github.com/ChipmunkCraft/ChipDevTools/issues/new?assignees=&labels=enhancement&template=FEATURE_REQUEST.md) | [Submit Bug Report](https://github.com/ChipmunkCraft/ChipDevTools/issues/new?assignees=&labels=bug&template=BUG_REPORT.md) ]