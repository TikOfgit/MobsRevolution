package listeners;

import logicgames.GameLogic;
import logicgames.MobPowers;
import logicgames.PlayerData;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Player;

public class PowerActivationListener implements Listener {
    private GameLogic gameLogic;

    public PowerActivationListener(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = gameLogic.getPlayerData(player);

        if (playerData != null) {
            MobPowers.activatePower(player, playerData.getCurrentMob());
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getEntity();
            if (snowball.getShooter() instanceof Player) {
                event.getEntity().getWorld().getBlockAt(event.getEntity().getLocation()).setType(Material.WEB);
            }
        }
    }
}
