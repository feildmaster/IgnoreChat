package feildmaster.IgnoreChat;

import java.util.HashSet;
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
        String p = event.getPlayer().getName();
        Set<Player> removed = new HashSet<Player>();

        for(Player r : event.getRecipients())
            if((ignoreList.containsKey(p) && ignoreList.get(p).contains(r.getName()))
                    ||(ignoreList.containsKey(r.getName()) && ignoreList.get(r.getName()).contains(p)))
                removed.add(r);

        if(!removed.isEmpty())
            event.getRecipients().removeAll(removed);
  }
}