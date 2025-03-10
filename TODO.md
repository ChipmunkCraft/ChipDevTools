## Item Tag Lookup - <sub>*Planned for v2.1.0*</sub>
- **What:** Grab all item IDs under a specific tag (e.g., minecraft:planks) and copy/export them.
- **Why:** Modpack devs tweak tags a lot (e.g., unifying resources across mods). Looking up tag contents manually is a slog—your tool could automate it.
- **How:**
  - Command: /cdt tags <tag_name> (e.g., /cdt tags minecraft:planks).
  - Uses TagKey<Item> and ItemTags to fetch registered items.
  - Output to clipboard or file.
- **Effort:** Low—NeoForge’s tag system is accessible, just needs parsing.


## Block/Item Property Inspector - <sub>*Planned for v2.1.0*</sub>
- **What:** Select an item/block (hand or looked-at) and dump its properties (e.g., hardness, blast resistance, harvest tool) to clipboard/file.
- **Why:** Devs tweak block behaviors (e.g., KubeJS configs)—fetching properties manually is tedious.
- **How:**
  - Command: /cdt inspect.
  - Hotkey: Ctrl + P (properties).
  - Pull from BlockState/ItemStack (e.g., block.getDestroySpeed()).
- **Effort:** Low—mostly data extraction.

## Searchable Item Browser - <sub>*Planned for v2.2.0 or higher*</sub>
- **What:** A GUI to search items by name/ID/tag, then copy/export selected ones.
- **Why:** Faster than scanning inventories or JEI for obscure items.
- **How:**
  - Hotkey: Ctrl + Shift + S (search).
  - Use Registry.ITEM and filter by string/tag.
  - Effort: High—GUI dev + search logic.