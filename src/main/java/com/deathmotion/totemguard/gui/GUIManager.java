package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
// gui manager
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
        if (!player.isOp()) {
            player.sendMessage(plugin.getMessengerService().format(
                plugin.getMessengerService().getPrefix() + " &#ff0000You must be OP to use the GUI!"
            ));
            return;
        }
        mainGUI.open(player);
    }

    public void openSettingsGUI(Player player) {
        if (!player.isOp()) {
            player.sendMessage(plugin.getMessengerService().format(
                plugin.getMessengerService().getPrefix() + " &#ff0000You must be OP to use the GUI!"
            ));
            return;
        }
        settingsGUI.open(player);
    }

    public void openFlaggedUsersGUI(Player player) {
        if (!player.isOp()) {
            player.sendMessage(plugin.getMessengerService().format(
                plugin.getMessengerService().getPrefix() + " &#ff0000You must be OP to use the GUI!"
            ));
            return;
        }
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
