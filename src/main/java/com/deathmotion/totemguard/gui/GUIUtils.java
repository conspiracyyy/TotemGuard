package com.deathmotion.totemguard.gui;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
// utils
public class GUIUtils {
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .character('&')
            .hexCharacter('#')
            .useUnusualXRepeatedCharacterHexFormat()
            .hexColors()
            .build();

    public static ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(color(name));
            if (lore != null && !lore.isEmpty()) {
                meta.setLore(lore.stream().map(GUIUtils::color).toList());
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack createItem(Material material, String name) {
        return createItem(material, name, new ArrayList<>());
    }

    public static ItemStack createBlankPane() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(" ");
            item.setItemMeta(meta);
        }
        return item;
    }

    public static String color(String text) {
        // Replace &# with # for hex codes, then use legacy serializer
        String processed = text.replace("&#", "#");
        return LEGACY_SERIALIZER.serialize(LEGACY_SERIALIZER.deserialize(processed));
    }
}
