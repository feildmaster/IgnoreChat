package feildmaster.IgnoreChat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Ignore extends JavaPlugin {
    private Map<String, List<String>> ignoreList = new HashMap<String, List<String>>();

    public void onDisable() {
        System.out.println("[IgnoreChat] Disabled");
    }

    public void onEnable() {
        // Chat listener
        getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new IgnoreListener(this), Priority.Normal, this);

        // Setup commands
        getCommand("ignore").setExecutor(new IgnoreCommand(this));
        getCommand("ignore-list").setExecutor(new ListCommand(this));

        System.out.println("[IgnoreChat] Enabled");
    }

    public Map<String, List<String>> getList() {
        return ignoreList;
    }
}