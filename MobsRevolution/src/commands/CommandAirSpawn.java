package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandAirSpawn implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public CommandAirSpawn(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            config.set("lobby.spawn", player.getLocation());
            player.sendMessage("Point de lobby défini !");
            plugin.saveConfig();
            return true;
        }
        return false;
    }
}
