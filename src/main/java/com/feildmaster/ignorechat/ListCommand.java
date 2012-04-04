package com.feildmaster.ignorechat;

import java.util.Iterator;
import org.bukkit.command.*;

class ListCommand implements CommandExecutor {
    private final Ignore plugin;

    public ListCommand(Ignore p) {
        plugin = p;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("ignore.use")) {
            return false;
        }

        StringBuilder string = new StringBuilder();
        if (plugin.getList().containsKey(sender.getName())) {
            for (Iterator<String> it = plugin.getList().get(sender.getName()).iterator(); it.hasNext();) {
                String name = it.next();
                string.append(string.length() != 0 ? ", " : "").append(name);
            }
        }

        if (string.length() == 0) {
            string.append("No one");
        }

        sender.sendMessage(string.insert(0, "You are ignoring: ").toString());

        return true;
    }
}
