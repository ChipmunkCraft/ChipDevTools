## `v2.0.1` <sub>03/10/2025 13:20</sub>
- Updated to NeoForge v21.1.133 (Minecraft 1.21.1)
- Removed unnecessary debug logging (log spam)
- Added disable copy hotkeys hotkey

## `v2.0.0` <sub>*03/10/2025 01:56*</sub>
- Renamed from ChipDevTools to ChipsDevTools
- Updated to NeoForge v21.1.132 (Minecraft 1.21.1 | ModDevGradle 2.0.78)
- Major refactor of gradle environment to function as a mod dev template
- Added addonexample as an optional addon template
- Run gradlew build before :addonexample:build if ChipsDevTools isn’t built
- Addons will not run any tasks unless explicitly asked to (cleanAll and buildAll will)
- Build jars are copied to finalJars
- Added `DevAuth` for Microsoft account login in dev runs
- Optimized build process with Grok’s help—thanks!
- Fixed container close bug [#2](../../issues/2)
- Rewrote `ItemIDs.java` with better OOP structure for flexibility
- Optimized `ItemIDs` with `StringBuilder` and dynamic refs
- Switched hotkeys to ScreenEvent.KeyPressed with matches() for GUI-only functionality
- Unified /cdt commands and hotkeys via runCDT (Ctrl+M=hand, O=offhand, H=hotbar, I=inventory, C=container, A=all)

## `v1.0.2` <sub>*03/04/2025 9:13*</sub>
- Updated to NeoForge v21.1.127 (Minecraft 1.21.1)

## `v1.0.1`
- Fixed StringIndexOutOfBoundsException by skipping \n subString when no items are being copied
- Tested compatibility with NeoForge v20.4.234

## `v1.0`
- Ported to NeoForge v20.4.232

## `v0.2`
- Updated to Forge 49.0.38 (Minecraft 1.20.4)
- Added "offhand" argument for CDT command
- Added chest inventory copy hot key "Default: Ctrl+C"

## `v0.1`
- Initial
