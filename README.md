<div align="center">
  <h1>TotemGuard</h1>
</div>

## Overview
This is the unofficial fork of totem guard with some extra features i added... What did i add? I added a new GUI menu to manage all this in a GUI
TotemGuard is a lightweight anti-cheat plugin designed to detect players using AutoTotem. It operates asynchronously to
minimize server impact and offers extensive configurability, enabling server owners to tailor the plugin to their
specific needs.

### Prerequisites

TotemGuard requires the [PacketEvents](https://modrinth.com/plugin/packetevents) library to function. Ensure it is
installed on your server.


## Supported Platforms & Versions

| Platform                        | Supported Versions |
|---------------------------------|--------------------|
| Paper, Folia, and related forks | 1.16.5 - 26.1      |

### AutoTotem

- **AutoTotemA** - Click time difference
- **AutoTotemB** - Impossible standard deviation
- **AutoTotemC** - Impossible average standard deviation
- **AutoTotemD** - Suspicious re-totem packet sequence
- **AutoTotemE** - Impossible low outliers
- **AutoTotemF** - Invalid interactions during inventory close

### BadPackets

- **BadPacketsA** - Opt-out message in a mod configuration channel
- **BadPacketsB** - Banned client brand
- **BadPacketsC** - Same slot change packet
- **BadPacketsD** - Player is faking the usage of Lunar Client

### Mods

- **AccurateBlockPlacement** - Usage of the Accurate Block Placement Reborn mod: https://modrinth.com/mod/accurate-block-placement-reborn

### ManualTotem

- **ManualTotemA** - Time difference between replacement after totem removal

## Features

- **Performance** - Asynchronous operations ensure minimal impact on server performance.
- **Database Support** - Compatible with both MySQL and SQLite.
- **Folia Integration** - Supports [Folia](https://papermc.io/software/folia) for regionized multithreading.
- **Webhooks** - Send alerts and punishments to a Discord webhook.
- **Highly Configurable** - Adjust nearly every setting during runtime to fit your server's needs.
- **Update Checker** - Automatically checks for updates on startup.
- **Bypass Permission** - Allows players with `TotemGuard.Bypass` to bypass checks.
- **Cross Server Alerts** - Easily send and retrieve alerts across multiple servers, through either plugin messaging or
  Redis.
- **Bedrock Exception** - Automatically ignores Bedrock Edition players to prevent false positives.

## Commands

- `/totemguard gui` Command for GUI
- `/totemguard` or `/tg` - Main command for TotemGuard.
- `/totemguard reload` - Reload the plugin configuration.
- `/totemguard alerts` - Toggle alerts for the player.
- `/totemguard check <player>` - Check the player for AutoTotem.
- `/totemguard alerts <player>` - Toggle alerts for another player.
- `/totemguard profile` - Display the player's profile.
- `/totemguard stats` - Show plugin statistics.
- `/totemguard clearlogs` - Clear the logs.
- `/totemguard track/untrack <player>` - Tracks the player.
- `/totemguard top` - Shows the top 10 violators.
- `/totemguard manualban <player>` - Manually ban a player.
- `/totemguard database trim` - Trim the database.
- `/totemguard database clear` - Clear the database.

## Permission Nodes

Operators (OPs) have these permissions by default, except `TotemGuard.Debug`:

- `TotemGuard.*` - Access to all TotemGuard permissions.
- `TotemGuard.Staff` - Access to `TotemGuard.Check`, `TotemGuard.Alerts`, and `TotemGuard.Profile`.
- `TotemGuard.Databases.*` - Access to all database-related commands.
- `TotemGuard.Reload` - Access to the `/totemguard reload` command.
- `TotemGuard.Check` - Access to the `/totemcheck` command.
- `TotemGuard.Alerts` - Access to the `/totemguard alerts` command.
- `TotemGuard.Alerts.Others` - Toggle alerts for other players.
- `TotemGuard.Profile` - Access to the `/totemguard profile` command.
- `TotemGuard.Stats` - Access to the `/totemguard stats` command.
- `TotemGuard.ClearLogs` - Access to the `/totemguard clearlogs` command.
- `TotemGuard.Track` - Access to the `/totemguard track` command.
- `TotemGuard.Bypass` - Bypass the plugin's checks.
- `TotemGuard.Update` - Receive update notifications.
- `TotemGuard.Top` - Access to the `/totemguard top` command.
- `TotemGuard.ManualBan` - Access to the `/totemguard manualban` command.
- `TotemGuard.Database.Trim` - Access to the `/totemguard database trim` command.
- `TotemGuard.Database.Clear` - Access to the `/totemguard database clear` command.
- `TotemGuard.Debug` - View debug messages.
