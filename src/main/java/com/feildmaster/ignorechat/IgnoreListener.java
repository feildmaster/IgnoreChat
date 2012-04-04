package com.feildmaster.ignorechat;

import java.util.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerChatEvent;

class IgnoreListener implements Listener {
    private final Ignore plugin;

    public IgnoreListener(Ignore p) {
        plugin = p;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        if (event.isCancelled()) return;

        Map<String, List<String>> ignoreList = plugin.getList();
        String p = event.getPlayer().getName();
        List<String> playerIgnores = ignoreList.get(p);

        for (Iterator<Player> it = event.getRecipients().iterator(); it.hasNext();) {
            Player r = it.next();
            List<String> recipientIgnores = ignoreList.get(r.getName());

            boolean playerIgnoresRecipient = playerIgnores != null && playerIgnores.contains(r.getName());
            boolean recipientIgnoresPlayer = recipientIgnores != null && recipientIgnores.contains(p);
            if (playerIgnoresRecipient || recipientIgnoresPlayer) {
                it.remove();
            }
        }
    }
}