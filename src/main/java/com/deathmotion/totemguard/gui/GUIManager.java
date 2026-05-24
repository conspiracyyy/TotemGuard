package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GUIManager {
    private final TotemGuard plugin;
    private final MainGUI mainGUI;
    private final SettingsGUI settingsGUI;
    private final FlaggedUsersGUI flaggedUsersGUI;

    public GUIManager(TotemGuard plugin) {
        this.plugin = plugin;
        this.mainGUI = new MainGUI(plugin, this);
        this.settingsGUI = new SettingsGUI(plugin, this);
        this.flaggedUsersGUI = new FlaggedUsersGUI(plugin, this);
    }

    public void openMainGUI(Player player) {
        mainGUI.open(player);
    }

    public void openSettingsGUI(Player player) {
        settingsGUI.open(player);
    }

    public void openFlaggedUsersGUI(Player player) {
        flaggedUsersGUI.open(player);
    }

    public void refreshAll() {
        mainGUI.refresh();
        settingsGUI.refresh();
        flaggedUsersGUI.refresh();
    }

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public SettingsGUI getSettingsGUI() {
        return settingsGUI;
    }

    public FlaggedUsersGUI getFlaggedUsersGUI() {
        return flaggedUsersGUI;
    }
}
