# ChipsDevTools v2.0.0
### Chipmunks' Development Tools for Minecraft/NeoForge Mod/Pack Development

Minecraft Version: ***1.21.1+*** | Neoforge Version: ***21.1.132+***

## Features:
* Adds /cdt command
  * ***Arguments:***
    * *hand*
    * *offhand*
    * *hotbar*
    * *inventory*
    * *all*

The ***/cdt hand*** command for example will get a formatted Description ID of the item in your hand and automatically copy it to your clipboard as long as you are holding an item.  
Let's say you are holding cobblestone. Running ***/cdt hand*** will put ***minecraft:cobblestone*** into your clipboard.

The ***/cdt offhand*** command will accomplish the same but for the item in your offhand  
Or, you run ***/cdt hotbar***; will iterate through each slot in the hotbar, find the items in the hotbar and will put each Description ID in your clipboard.

***Example:*** You have cobblestone, an apple and furnace in your hotbar.  This will put the following into your Clipboard *(each item is on its' own line for multi-line editing convience)*:
>minecraft:cobblestone \n minecraft:apple \n minecraft:furnace

For the argument ***inventory***; it will get each item in the players upper inventory section.
Argument ***all*** will do both the hotbar, offhand and upper player inventory.

*Ctrl+C* can be used within a chest or similar inventory menu to copy all item IDs of both the player inventory and the chest containers.

This is useful for modders/modpack makers who may need to do common tasks without having to manually enter each item description id.
The true power here is when you are using an IDE/text editor that supports multi-line editing.

## Template Setup
- Run `gradlew build` to build `ChipsDevTools`
- For addons (e.g., `addonexample`)
  - Uncomment `include 'addonexample'` in `settings.gradle`
  - Run `gradlew :addonexample:build` (ensure `ChipsDevTools` is built first)
  - Run `gradlew :addonexample:runClient` to test

## Template Notes
- Addon tasks (e.g., `build`, `runClient`) only run when explicitly called
- JARs are copied to root `finalJars` for convenience
- ***Remove this mod before publishing your own modpack!***

Please feel free to put in a feature request through the option below (*feature suggestions are not guaranteed to be implemented*)

[ [Feature Request](https://github.com/ChipmunkCraft/ChipsDevTools/issues/new?assignees=&labels=enhancement&template=FEATURE_REQUEST.md) | [Submit Bug Report](https://github.com/ChipmunkCraft/ChipsDevTools/issues/new?assignees=&labels=bug&template=BUG_REPORT.md) ]