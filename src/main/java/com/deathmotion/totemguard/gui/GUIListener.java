package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
// just the listener
public class GUIListener implements Listener {
    private final TotemGuard plugin;
    private final GUIManager guiManager;

    public GUIListener(TotemGuard plugin, GUIManager guiManager) {
        this.plugin = plugin;
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        String title = event.getView().getTitle();
        if (!title.contains("🌊")) return;

        event.setCancelled(true);

        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        String itemName = clicked.getItemMeta().getDisplayName();
        if (itemName == null) return;

        if (title.contains("TOTEMGUARD MENU")) {
            handleMainMenuClick(player, itemName);
        } else if (title.contains("SETTINGS")) {
            handleSettingsClick(player, itemName);
        } else if (title.contains("FLAGGED USERS")) {
            handleFlaggedUsersClick(player, itemName);
        }
    }

    private void handleMainMenuClick(Player player, String itemName) {
        if (itemName.contains("SETTINGS")) {
            guiManager.openSettingsGUI(player);
        } else if (itemName.contains("REFRESH")) {
            guiManager.refreshAll();
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aGUI refreshed successfully!"));
            guiManager.openMainGUI(player);
        } else if (itemName.contains("FLAGGED USERS")) {
            guiManager.openFlaggedUsersGUI(player);
        }
    }

    private void handleSettingsClick(Player player, String itemName) {
        if (itemName.contains("EXIT")) {
            guiManager.openMainGUI(player);
        } else if (itemName.contains("DEBUG MODE")) {
            plugin.getConfigManager().getSettings().setDebug(!plugin.getConfigManager().getSettings().isDebug());
            guiManager.getSettingsGUI().refresh();
            guiManager.openSettingsGUI(player);
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aDebug mode " + (plugin.getConfigManager().getSettings().isDebug() ? "enabled" : "disabled") + "!"));
        } else if (itemName.contains("ALERTS")) {
            plugin.getConfigManager().getSettings().setAlertsEnabled(!plugin.getConfigManager().getSettings().isAlertsEnabled());
            guiManager.getSettingsGUI().refresh();
            guiManager.openSettingsGUI(player);
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aAlerts " + (plugin.getConfigManager().getSettings().isAlertsEnabled() ? "enabled" : "disabled") + "!"));
        } else if (itemName.contains("AUTOTOTEM")) {
            boolean newState = !plugin.getConfigManager().getChecks().getAutoTotemA().isEnabled();
            plugin.getConfigManager().getChecks().getAutoTotemA().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemB().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemC().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemD().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemE().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemF().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemG().setEnabled(newState);
            plugin.getConfigManager().getChecks().getAutoTotemH().setEnabled(newState);
            guiManager.getSettingsGUI().refresh();
            guiManager.openSettingsGUI(player);
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aAutoTotem checks " + (newState ? "enabled" : "disabled") + "!"));
        } else if (itemName.contains("BADPACKETS")) {
            boolean newState = !plugin.getConfigManager().getChecks().getBadPacketsA().isEnabled();
            plugin.getConfigManager().getChecks().getBadPacketsA().setEnabled(newState);
            plugin.getConfigManager().getChecks().getBadPacketsB().setEnabled(newState);
            plugin.getConfigManager().getChecks().getBadPacketsC().setEnabled(newState);
            plugin.getConfigManager().getChecks().getBadPacketsD().setEnabled(newState);
            guiManager.getSettingsGUI().refresh();
            guiManager.openSettingsGUI(player);
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aBadPackets checks " + (newState ? "enabled" : "disabled") + "!"));
        } else if (itemName.contains("WEBHOOKS")) {
            boolean newState = !plugin.getConfigManager().getWebhooks().getAlert().isEnabled();
            plugin.getConfigManager().getWebhooks().getAlert().setEnabled(newState);
            plugin.getConfigManager().getWebhooks().getPunishment().setEnabled(newState);
            guiManager.getSettingsGUI().refresh();
            guiManager.openSettingsGUI(player);
            player.sendMessage(plugin.getMessengerService().format(plugin.getMessengerService().getPrefix() + " &#ff7a0aWebhooks " + (newState ? "enabled" : "disabled") + "!"));
        }
    }

    private void handleFlaggedUsersClick(Player player, String itemName) {
        if (itemName.contains("EXIT")) {
            guiManager.openMainGUI(player);
        } else if (itemName.contains("🌊")) {
            // Extract player name from the display name
            String targetName = itemName.replace("&#0aff35&l🌊 ", "");
            org.bukkit.entity.Player target = Bukkit.getPlayer(targetName);
            
            if (target != null && target.isOnline()) {
                // Save current gamemode
                org.bukkit.GameMode previousGamemode = player.getGameMode();
                
                // Teleport to target
                player.teleport(target.getLocation());
                
                // Set to spectator mode
                player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                
                player.sendMessage(plugin.getMessengerService().format(
                    plugin.getMessengerService().getPrefix() + " &#ff7a0aTeleported to " + targetName + " in spectator mode!"
                ));
            } else {
                player.sendMessage(plugin.getMessengerService().format(
                    plugin.getMessengerService().getPrefix() + " &#ff0000Player " + targetName + " is not online!"
                ));
            }
        }
    }
}
