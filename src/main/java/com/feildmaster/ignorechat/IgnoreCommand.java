package com.feildmaster.ignorechat;

import java.util.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

class IgnoreCommand extends AbstractCommand {
    public IgnoreCommand(final Ignore p) {
        super(p, "ignore", "ignore.use");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to do this");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /" + label + " [Player]");
            return false;
        }

        if (!plugin.getServer().isPrimaryThread()) {
            throw new IllegalStateException("Commands can only be performed on the main thread!");
        }

        Player player = (Player) sender;
        Player query = plugin.getServer().getPlayer(args[0]);
        if(query == null) {
            plugin.sendMessage(sender, args[0] + " not found");
            return false;
        } else if (player == query) {
            plugin.sendMessage(sender, "You can not ignore yourself");
            return false;
        } else if (query.hasPermission("ignore.block")) {
            plugin.sendMessage(sender, "This player can not be ignored");
            return false;
        }

        String queryName = query.getName();

        List<String> list = plugin.getList(player);
        if(list.contains(queryName)) {
            list.remove(queryName);
            plugin.sendMessage(sender, String.format("No longer ignoring %s", queryName));
        } else {
            list.add(queryName);
            plugin.sendMessage(sender, String.format("Ignoring %s", queryName));
        }
        return true;
    }
}