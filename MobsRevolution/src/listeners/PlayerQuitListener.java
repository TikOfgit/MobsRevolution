package listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import mobsrevolution.Main;

public class PlayerQuitListener implements Listener {
    private Main plugin;

    public PlayerQuitListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Mettre à jour le tableau d'affichage du lobby pour refléter le nombre actuel de joueurs
        plugin.getLobbyScoreboard().updatePlayerCount(Bukkit.getOnlinePlayers().size() - 1);
    }
}
