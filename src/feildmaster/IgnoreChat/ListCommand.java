package feildmaster.IgnoreChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class ListCommand implements CommandExecutor {
    private final Ignore plugin;

    public ListCommand(Ignore p) {
        plugin = p;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!((Player)sender).hasPermission("ignore.use")) return false;

        StringBuilder string = new StringBuilder();
        if(plugin.getList().containsKey(((Player)sender).getName()))
            for(String name : plugin.getList().get(((Player)sender).getName()))
                string.append(string.length()!=0?", ":"").append(name);

        if(string.length() == 0)
            string.append("No one");

        sender.sendMessage(string.insert(0, "You are ignoring: ").toString());

        return true;
    }

}
