package com.feildmaster.ignorechat;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

class MuteCommand extends AbstractCommand implements TabCompleter {
    private final String[] silenceList;
    private static final String SILENT_PERMISSION = "ignore.mute.silent";

    MuteCommand(Ignore plugin) {
        super(plugin, "mute", "ignore.mute");

        List<String> list = new ArrayList<String>();
        for (String key :plugin.getConfig().getStringList("mute-command-silent-keywords")) {
            list.add(key);
        }

        if (list.isEmpty()) {
            list.add("silent");
        }

        silenceList = list.toArray(new String[list.size()]);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendUsage(sender, sender.hasPermission(SILENT_PERMISSION) ? " [" + StringUtils.join(silenceList, "|") + "]" : "");
            return true;
        }

        Player mutee = plugin.getServer().getPlayer(args[0]);
        boolean isSilent = args.length > 1 && findString(args[1], silenceList);

        if (mutee == null) {
            plugin.sendMessage(sender, "Player not found: " + args[0]);
        } else {
            if (mutee.hasPermission(IGNORE_BLOCK_PERMISSION) || sender == mutee) {
                plugin.sendMessage(sender, "Can not mute " + (sender != mutee ? mutee.getName() : "yourself"));
            } else {
                if (plugin.toggleMuted(mutee, isSilent)) {
                    plugin.sendMessage(sender, mutee.getName() + " is now muted");
                } else {
                    plugin.sendMessage(sender, mutee.getName() + " is now un-muted");
                }
            }
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return args.length == 1 ? null : args.length == 2 && sender.hasPermission(SILENT_PERMISSION) ? ImmutableList.<String>copyOf(silenceList) : ImmutableList.<String>of();
    }
}
