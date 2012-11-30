package com.feildmaster.ignorechat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class MeCommand extends AbstractCommand {
    MeCommand(final Ignore p) {
        super(p, "me", "bukkit.command.me");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length == 0) {
            return false; // Pass to vanilla
        }

        StringBuilder message = new StringBuilder();
        message.append("* ").append(((Player) sender).getDisplayName());
        for (String arg : args) {
            message.append(" ");
            message.append(arg);
        }

        broadcastMessage(sender, message.toString());

        return true;
    }
}
