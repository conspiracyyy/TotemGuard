package com.deathmotion.totemguard.commands.impl;

import com.deathmotion.totemguard.TotemGuard;
import com.deathmotion.totemguard.commands.AbstractCommand;
import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

public class GuiCommand extends AbstractCommand {
    private final TotemGuard plugin;

    public GuiCommand(TotemGuard plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register(LegacyPaperCommandManager<CommandSender> commandManager) {
        commandManager.command(root(commandManager)
                .literal("gui")
                .handler(this::handle));
    }

    private void handle(@NonNull CommandContext<CommandSender> context) {
        if (!(context.sender() instanceof Player)) {
            context.sender().sendMessage("This command can only be used by players.");
            return;
        }

        Player player = (Player) context.sender();
        plugin.getGuiManager().openMainGUI(player);
    }
}
