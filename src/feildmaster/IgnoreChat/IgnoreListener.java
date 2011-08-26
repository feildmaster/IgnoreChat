package feildmaster.IgnoreChat;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

class IgnoreListener extends PlayerListener {
    private final Ignore plugin;
    public IgnoreListener(Ignore p) {
        plugin = p;
    }

    public void onPlayerChat(PlayerChatEvent event) {
        if(event.isCancelled()) return;

        Map<String, List<String>> ignoreList = plugin.getList();
        Set<Player> recipients = event.getRecipients();
        String p = event.getPlayer().getName();

        for(Player r : recipients)
            if((ignoreList.containsKey(p) && ignoreList.get(p).contains(r.getName()))
                    ||(ignoreList.containsKey(r.getName()) && ignoreList.get(r.getName()).contains(p)))
                recipients.remove(r);
  }
}