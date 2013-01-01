package com.feildmaster.ignorechat;

import com.feildmaster.event.EmoteEvent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class MeCommand extends AbstractCommand {
    MeCommand(final Ignore p) {
        super(p, "me", "bukkit.command.me");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return false; // Pass to vanilla
        }

        final String displayName = sender instanceof Player ? ((Player) sender).getDisplayName() : sender.getName();

        StringBuilder builder = new StringBuilder();
        builder.append("* ").append(displayName).append(" ");
        builder.append(StringUtils.join(args, " "));

        if (EmoteEvent.getHandlerList().getRegisteredListeners().length > 0) {
            EmoteEvent event = new EmoteEvent(sender, builder.toString(), getRecipients(sender));
            plugin.getServer().getPluginManager().callEvent(event);
            final String message = event.getMessage();
            broadcast(message, event.getRecipients());
        } else {
            broadcast(builder.toString(), getRecipients(sender));
        }

        return true;
    }
}
