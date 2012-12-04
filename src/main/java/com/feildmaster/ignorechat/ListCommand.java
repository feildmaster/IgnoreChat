package com.feildmaster.ignorechat;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.*;

class ListCommand extends AbstractCommand {
    ListCommand(Ignore p) {
        super(p, "ignore-list", "ignore.use");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!plugin.getServer().isPrimaryThread()) {
            throw new IllegalStateException("Commands can only be performed on the main thread!");
        }

        StringBuilder string = new StringBuilder();
        List<String> list = plugin.getList(sender);

        string.append("You are ignoring:").append(" ");

        if (list.isEmpty()) {
            string.append("No one");
        } else {
            string.append(StringUtils.join(list, ", "));
        }

        plugin.sendMessage(sender, string.toString());

        return true;
    }
}
