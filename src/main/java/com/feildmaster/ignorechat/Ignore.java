package com.feildmaster.ignorechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Ignore extends JavaPlugin {
    private Map<String, List<String>> ignoreList = new ConcurrentHashMap<String, List<String>>(16, 0.75f, 1); // Only one thread (The main) is able to edit
    private Map<String, Boolean> muteList = new ConcurrentHashMap<String, Boolean>(16, 0.75f, 1);
    private static String pluginName;

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

        // mute command
        new MuteCommand(this);

        pluginName = getDescription().getName();
    }

    Map<String, List<String>> getList() {
        return ignoreList;
    }

    @Override
    public FileConfiguration getConfig() {
        checkAccess("getConfig()");
        return super.getConfig();
    }

    /**
     * Read/Write (upon creation) function for editing list, can only be called on main thread!
     */
    List<String> getList(CommandSender sender) {
        checkAccess("getList(CommandSender)");

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

    /**
     * Read/Write function for toggling mutes, can only be called on main thread!
     */
    boolean toggleMuted(CommandSender sender, boolean silent) {
        checkAccess("toggleMuted(CommandSender)");

        boolean mute = !isMuted(sender);

        if (mute) {
            muteList.put(sender.getName(), true);
        } else {
            muteList.put(sender.getName(), false);
        }

        if (!silent) {
            sender.sendMessage(mute ? "You have been muted" : "You have been un-muted");
        }

        return mute;
    }

    /**
     * Read only function to see if sender is muted
     */
    boolean isMuted(CommandSender cs) {
        return muteList.containsKey(cs.getName()) && muteList.get(cs.getName()) == true;
    }

    void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(String.format("[%s] %s", pluginName, message));
    }

    void checkAccess(String methodName) {
        if (!getServer().isPrimaryThread()) {
            throw new IllegalStateException(methodName + " can only be called on the main thread!");
        }
    }
}