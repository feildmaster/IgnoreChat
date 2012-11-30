package com.feildmaster.ignorechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Ignore extends JavaPlugin {
    private Map<String, List<String>> ignoreList = new ConcurrentHashMap<String, List<String>>(16, 0.75f, 1); // Only one thread (The main) is able to edit
    private String pluginName;

    public void onEnable() {
        // Chat listener
        new IgnoreListener(this);

        // ignore command
        new IgnoreCommand(this);

        // ignore-list command
        new ListCommand(this);

        // me command
        new MeCommand(this);

        // tell command
        new TellCommand(this);

        pluginName = getDescription().getName();
    }

    Map<String, List<String>> getList() {
        return ignoreList;
    }

    /**
     * Read/Write (upon creation) function for editing list, can only be called on main thread!
     */
    List<String> getList(CommandSender sender) {
        if (!getServer().isPrimaryThread()) {
            throw new IllegalStateException("Get list funciton can only be called on the main thread!");
        }

        String key = sender.getName();
        List<String> playerList = ignoreList.get(key);

        if (playerList == null) {
            playerList = new ArrayList<String>();
            ignoreList.put(key, playerList);
        }

        return playerList;
    }

    /**
     * Read only function to see if recipient is ignoring sender
     */
    boolean isIgnoring(CommandSender recipient, CommandSender sender) {
        List<String> playerList = ignoreList.get(recipient.getName());
        if (playerList == null) {
            return false;
        } else {
            return playerList.contains(sender.getName());
        }
    }

    void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(String.format("[%s] %s", pluginName, message));
    }
}