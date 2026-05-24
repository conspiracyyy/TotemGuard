package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
// main gui
public class MainGUI {
    private final TotemGuard plugin;
    private final GUIManager guiManager;
    private Inventory inventory;

    public MainGUI(TotemGuard plugin, GUIManager guiManager) {
        this.plugin = plugin;
        this.guiManager = guiManager;
        createInventory();
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(null, 27, GUIUtils.color("&#00B3FF&l🌊 TOTEMGUARD MENU"));

        List<String> settingsLore = Arrays.asList(
                "",
                "&8View TotemGuard settings:",
                "",
                "&#3962d4⏵ &fClick here to proceed! &#3962d4⏴"
        );

        ItemStack settingsButton = GUIUtils.createItem(Material.FLOW_BANNER_PATTERN, GUIUtils.color("&#00B3FF&l🌊 SETTINGS"), settingsLore);
        inventory.setItem(15, settingsButton);

        List<String> refreshLore = Arrays.asList(
                "",
                "&8Refresh TotemGuards settings:",
                "",
                "&#3962d4⏵ &fClick here to proceed! &#3962d4⏴"
        );

        ItemStack refreshButton = GUIUtils.createItem(Material.COMPASS, GUIUtils.color("&#ff7a0a&l🌊 REFRESH"), refreshLore);
        inventory.setItem(13, refreshButton);

        List<String> flaggedLore = Arrays.asList(
                "",
                "&8View flags:",
                "",
                "&#3962d4⏵ &fClick here to proceed! &#3962d4⏴"
        );

        ItemStack flaggedButton = GUIUtils.createItem(Material.PLAYER_HEAD, GUIUtils.color("&#0aff35&l🌊 FLAGGED USERS"), flaggedLore);
        inventory.setItem(11, flaggedButton);
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
