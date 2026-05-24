package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import com.deathmotion.totemguard.config.Checks;
import com.deathmotion.totemguard.config.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsGUI {
    private final TotemGuard plugin;
    private final GUIManager guiManager;
    private Inventory inventory;

    public SettingsGUI(TotemGuard plugin, GUIManager guiManager) {
        this.plugin = plugin;
        this.guiManager = guiManager;
        createInventory();
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(null, 54, GUIUtils.color("&#00B3FF&l🌊 SETTINGS"));

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, GUIUtils.createBlankPane());
        }

        Settings settings = plugin.getConfigManager().getSettings();
        Checks checks = plugin.getConfigManager().getChecks();

        List<String> debugLore = Arrays.asList(
                "",
                "&8Debug Mode: " + (settings.isDebug() ? "&#0aff35&lENABLED" : "&#ff0000&lDISABLED"),
                "",
                "&#3962d4⏵ &fClick to toggle! &#3962d4⏴"
        );
        ItemStack debugButton = GUIUtils.createItem(Material.REDSTONE_TORCH, GUIUtils.color("&#00B3FF&l🌊 DEBUG MODE"), debugLore);
        inventory.setItem(10, debugButton);

        List<String> alertsLore = Arrays.asList(
                "",
                "&8Alerts Enabled: " + (settings.isAlertsEnabled() ? "&#0aff35&lENABLED" : "&#ff0000&lDISABLED"),
                "",
                "&#3962d4⏵ &fClick to toggle! &#3962d4⏴"
        );
        ItemStack alertsButton = GUIUtils.createItem(Material.BELL, GUIUtils.color("&#00B3FF&l🌊 ALERTS"), alertsLore);
        inventory.setItem(11, alertsButton);

        List<String> autoTotemLore = Arrays.asList(
                "",
                "&8AutoTotem Checks: " + (checks.getAutoTotemA().isEnabled() ? "&#0aff35&lENABLED" : "&#ff0000&lDISABLED"),
                "",
                "&#3962d4⏵ &fClick to toggle! &#3962d4⏴"
        );
        ItemStack autoTotemButton = GUIUtils.createItem(Material.TOTEM_OF_UNDYING, GUIUtils.color("&#00B3FF&l🌊 AUTOTOTEM CHECKS"), autoTotemLore);
        inventory.setItem(12, autoTotemButton);

        List<String> badPacketsLore = Arrays.asList(
                "",
                "&8BadPackets Checks: " + (checks.getBadPacketsA().isEnabled() ? "&#0aff35&lENABLED" : "&#ff0000&lDISABLED"),
                "",
                "&#3962d4⏵ &fClick to toggle! &#3962d4⏴"
        );
        ItemStack badPacketsButton = GUIUtils.createItem(Material.PAPER, GUIUtils.color("&#00B3FF&l🌊 BADPACKETS CHECKS"), badPacketsLore);
        inventory.setItem(13, badPacketsButton);

        List<String> webhookLore = Arrays.asList(
                "",
                "&8Webhooks: " + (plugin.getConfigManager().getWebhooks().getAlert().isEnabled() ? "&#0aff35&lENABLED" : "&#ff0000&lDISABLED"),
                "",
                "&#3962d4⏵ &fClick to toggle! &#3962d4⏴"
        );
        ItemStack webhookButton = GUIUtils.createItem(Material.ENCHANTED_BOOK, GUIUtils.color("&#00B3FF&l🌊 WEBHOOKS"), webhookLore);
        inventory.setItem(14, webhookButton);

        List<String> exitLore = Arrays.asList(
                "",
                "&8Exit this GUI",
                "",
                "&#3962d4⏵ &fClick here to proceed! &#3962d4⏴"
        );
        ItemStack exitButton = GUIUtils.createItem(Material.BARRIER, GUIUtils.color("&#FF0000&lEXIT"), exitLore);
        inventory.setItem(45, exitButton);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public void refresh() {
        createInventory();
    }

    public Inventory getInventory() {
        return inventory;
    }
}
