package com.feildmaster.ignorechat;

import java.util.List;
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

        string.append("You are ignoring: ");

        if (list.isEmpty()) {
            string.append("No one");
        } else {
            for (int x = 0; x < list.size(); x++) {
                if (x > 0) {
                    string.append(", ");
                }

                string.append(list.get(x));
            }
        }

        sender.sendMessage(string.toString());

        return true;
    }
}
