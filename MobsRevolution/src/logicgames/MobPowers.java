package logicgames;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Spider;
import org.bukkit.entity.IronGolem;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobPowers {
    public static void activatePower(Player player, MobType mobType) {
        switch (mobType) {
            case CREEPER:
                activateCreeperPower(player);
                break;
            case SPIDER:
                activateSpiderPower(player);
                break;
            case IRON_GOLEM:
                activateIronGolemPower(player);
                break;
            default:
                break;
        }
    }

    private static void activateCreeperPower(Player player) {
        // Lancer une TNT
        player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
    }

    private static void activateSpiderPower(Player player) {
        // Lancer des toiles d'araignée autour des joueurs
        player.getLocation().add(1, 0, 0).getBlock().setType(Material.WEB);
        player.getLocation().add(-1, 0, 0).getBlock().setType(Material.WEB);
        player.getLocation().add(0, 0, 1).getBlock().setType(Material.WEB);
        player.getLocation().add(0, 0, -1).getBlock().setType(Material.WEB);
    }

    private static void activateIronGolemPower(Player player) {
        // Créer un mur autour du joueur
        player.getLocation().add(1, 0, 0).getBlock().setType(Material.IRON_BLOCK);
        player.getLocation().add(-1, 0, 0).getBlock().setType(Material.IRON_BLOCK);
        player.getLocation().add(0, 0, 1).getBlock().setType(Material.IRON_BLOCK);
        player.getLocation().add(0, 0, -1).getBlock().setType(Material.IRON_BLOCK);
    }
}