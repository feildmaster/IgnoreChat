package com.feildmaster.ignorechat;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

abstract class AbstractCommand implements CommandExecutor {
    static final String IGNORE_BLOCK_PERMISSION = "ignore.block";

    final Ignore plugin;
    final PluginCommand command;

    AbstractCommand(Ignore p, String commandName, String permission) {
        plugin = p;
        command = plugin.getCommand(commandName);
        if (command != null) { // Don't do anything if the command has been disabled somehow
            command.setExecutor(this);
            command.setPermission(permission);
        } else {
            plugin.getLogger().log(Level.WARNING, "Failed to register command: {0}", commandName);
        }
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String label, String[] args);

    boolean findString(String string, String... contents) {
        for (String word : contents) {
            if (string.equalsIgnoreCase(word)) {
                return true;
            }
        }

        return false;
    }

    void broadcast(final String message, final Collection<CommandSender> recipients) {
        if (StringUtils.isBlank(message)) {
            return;
        }

        for (final CommandSender recipient : recipients) {
            if (recipient != null) {
                recipient.sendMessage(message);
            }
        }
    }

    Collection<CommandSender> getRecipients(CommandSender sender) {
        Player[] onlineList = plugin.getServer().getOnlinePlayers();
        ImmutableSet.Builder<CommandSender> builder = ImmutableSet.<CommandSender>builder();
        builder.add(sender); // Add the sender just in case

        for (Player player : onlineList) {
            if (//!player.canSee(playerSender) || // The player can't see the sender?
                    //plugin.isIgnoring(sender, player) || // The sender isIgnoring the player?
                    plugin.isIgnoring(player, sender)) { // Player is ignoring sender?
                continue;
            }

            builder.add(player);
        }

        return builder.build();
    }

    void sendUsage(CommandSender sender) {
        sendUsage(sender, "");
    }

    void sendUsage(CommandSender sender, String extra) {
        sender.sendMessage(command.getUsage() + extra);
    }
}
