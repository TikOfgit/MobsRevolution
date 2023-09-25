package listeners;

import logicgames.GameLogic;
import logicgames.MobPowers;
import logicgames.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PowerUseListener implements Listener {
    private GameLogic gameLogic;

    public PowerUseListener(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = gameLogic.getPlayerData(player);

        if (playerData != null) {
            MobPowers.activatePower(player, playerData.getCurrentMob());
        }
    }
}
