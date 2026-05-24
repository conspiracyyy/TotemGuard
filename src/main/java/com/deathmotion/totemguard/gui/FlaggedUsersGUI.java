package com.deathmotion.totemguard.gui;

import com.deathmotion.totemguard.TotemGuard;
import com.deathmotion.totemguard.models.TotemPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;
import java.util.stream.Collectors;

public class FlaggedUsersGUI {
    private final TotemGuard plugin;
    private final GUIManager guiManager;
    private Inventory inventory;

    public FlaggedUsersGUI(TotemGuard plugin, GUIManager guiManager) {
        this.plugin = plugin;
        this.guiManager = guiManager;
        createInventory();
    }

    private void createInventory() {
        inventory = Bukkit.createInventory(null, 54, GUIUtils.color("&#0aff35&l🌊 FLAGGED USERS"));

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, GUIUtils.createBlankPane());
        }

        List<TotemPlayer> flaggedPlayers = plugin.getPlayerDataManager().getPlayers().values().stream()
                .filter(p -> p.getTotalViolations() > 0)
                .sorted((a, b) -> Long.compare(b.getTotalViolations(), a.getTotalViolations()))
                .limit(45)
                .collect(Collectors.toList());

        int slot = 0;
        for (TotemPlayer player : flaggedPlayers) {
            if (slot >= 45) break;

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            if (meta != null) {
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
                meta.setDisplayName(GUIUtils.color("&#0aff35&l🌊 " + player.getName()));

                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&8Total Violations: &#ff0000&l" + player.getTotalViolations());
                lore.add("&8Client Brand: &#00B3FF&l" + player.getBrand());
                lore.add("&8Ping: &#ff7a0a&l" + player.getKeepAlivePing() + "ms");
                lore.add("");
                lore.add("&#3962d4⏵ &fClick for more info! &#3962d4⏴");

                meta.setLore(lore.stream().map(GUIUtils::color).collect(Collectors.toList()));
                head.setItemMeta(meta);
            }
            inventory.setItem(slot, head);
            slot++;
        }

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
