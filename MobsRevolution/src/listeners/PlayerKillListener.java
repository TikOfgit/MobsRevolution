package listeners;

import logicgames.GameLogic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import scoreboard.GameScoreboard;

public class PlayerKillListener implements Listener {
    private GameLogic gameLogic;

    public PlayerKillListener(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null) {
            gameLogic.playerKilled(killer);

            // Mettre à jour le tableau d'affichage
            GameScoreboard gameScoreboard = new GameScoreboard();
            gameScoreboard.updateForPlayer(killer, gameLogic.getPlayerData(killer));
        }
    }
}
