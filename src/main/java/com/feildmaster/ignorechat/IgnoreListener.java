package com.feildmaster.ignorechat;

import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.AsyncPlayerChatEvent;

class IgnoreListener implements Listener {
    private final Ignore plugin;

    IgnoreListener(Ignore p) {
        plugin = p;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) return;

        final Player sender = event.getPlayer();

        if (plugin.isMuted(sender)) {
            event.getRecipients().clear();
            event.getRecipients().add(sender);
            return;
        }

        Iterator<Player> iterator = event.getRecipients().iterator();
        while(iterator.hasNext()) {
            Player recipient = iterator.next();

            if (//!recipient.canSee(event.getPlayer()) || // Recipient can't see player? - (behavioral change)
                    // Sender is ignoring recipient? - This doesn't really make sense in this simple plugin
                    plugin.isIgnoring(recipient, sender)) { // Recipient is ignoring sender?
                iterator.remove();
            }
        }
    }
}