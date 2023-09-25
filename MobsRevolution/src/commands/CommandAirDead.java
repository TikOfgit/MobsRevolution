package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandAirDead implements CommandExecutor {

    private final JavaPlugin plugin;
    private final FileConfiguration config;

    public CommandAirDead(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            config.set("death.spawn", player.getLocation());
            player.sendMessage("Point de téléportation pour les joueurs morts défini !");
            plugin.saveConfig();
            return true;
        }
        return false;
    }
}

