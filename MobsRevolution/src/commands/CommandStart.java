package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import mobsrevolution.Main;
import game.GameManager;

public class CommandStart implements CommandExecutor {
    private Main plugin;
    private GameManager gameManager;

    public CommandStart(Main plugin) {
        this.plugin = plugin;
        this.gameManager = new GameManager(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                gameManager.startGameCountdown();
                return true;
            } else {
                player.sendMessage("Vous n'avez pas la permission d'utiliser cette commande.");
                return true;
            }
        }
        return false;
    }
}
