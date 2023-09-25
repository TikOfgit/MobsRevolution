package listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import mobsrevolution.Main;
import game.GameManager;

public class PlayerJoinListener implements Listener {
    private Main plugin;
    private GameManager gameManager;

    public PlayerJoinListener(Main plugin) {
        this.plugin = plugin;
        this.gameManager = new GameManager(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Téléporter le joueur au lobby si le point de lobby est défini
        if (plugin.getConfig().contains("lobby.spawn")) {
            Location lobbyLocation = (Location) plugin.getConfig().get("lobby.spawn");
            event.getPlayer().teleport(lobbyLocation);
        }
        
        // Assigner le tableau d'affichage du lobby au joueur et mettre à jour le compte des joueurs
        plugin.getLobbyScoreboard().assignToPlayer(event.getPlayer());
        plugin.getLobbyScoreboard().updatePlayerCount(Bukkit.getOnlinePlayers().size());
        
        // Démarrer le compte à rebours du jeu si 20 joueurs sont connectés
        if (Bukkit.getOnlinePlayers().size() == 20) {
            gameManager.startGameCountdown();
        }
    }
}