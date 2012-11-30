package com.feildmaster.ignorechat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellCommand extends AbstractCommand {
    TellCommand(Ignore plugin) {
        super(plugin, "tell", "bukkit.command.tell");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length < 2) {
            return false; // Send to vanilla
        }

        int index = 0;
        Player recipient = plugin.getServer().getPlayer(args[index++]); // Get player

        if (recipient == null) {
            plugin.sendMessage(sender, String.format(ChatColor.RED + "Could not find player: %s", args[index - 1]));
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int x = index; x < args.length; x++) {
            if (x > index) {
                message.append(" ");
            }
            message.append(args[x]);
        }

        String sendMessage = message.toString();

        String format = "%s%s%s: %s";
        sender.sendMessage(String.format(format, ChatColor.GRAY, "->", recipient.getDisplayName(), sendMessage));

        if (sender != recipient && !plugin.isIgnoring(recipient, sender)) { // Don't tell them if they're ignoring
            recipient.sendMessage(String.format(format, ChatColor.GRAY, "<-", ((Player) sender).getDisplayName(), sendMessage));
        }

        plugin.getServer().getConsoleSender().sendMessage(String.format(format, ChatColor.GRAY, String.format("%s->", ((Player) sender).getDisplayName()), recipient.getDisplayName(), sendMessage));

        return true;
    }
}
