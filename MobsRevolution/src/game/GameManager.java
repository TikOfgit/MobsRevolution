package game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import mobsrevolution.Main;
import scoreboard.GameScoreboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import logicgames.GameLogic;
import logicgames.PlayerData;

public class GameManager {
    private Main plugin;
    private FileConfiguration config;
    private boolean gameStarting = false;
    private GameLogic gameLogic;  // Ajout de la variable pour GameLogic

    public GameManager(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.gameLogic = new GameLogic();  // Instanciation de GameLogic
    }

    public void startGameCountdown() {
        if (gameStarting) return; // Si un compte à rebours est déjà en cours, ne faites rien.

        gameStarting = true;
        new BukkitRunnable() {
            int countdown = 15;

            @Override
            public void run() {
                switch (countdown) {
                    case 15:
                        Bukkit.broadcastMessage("§a[Announcement]§f : §cLe jeu commence dans §e15§c secondes.");
                        break;
                    case 10:
                        Bukkit.broadcastMessage("§a[Announcement]§f : §cLe jeu commence dans §e10§c secondes.");
                        break;
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        Bukkit.broadcastMessage("§a[Announcement]§f : §cLe jeu démarre dans §e" + countdown + "§c secondes.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage("§a[Announcement]§f : §cLe jeu commence dans §e1§c seconde.");
                        break;
                    case 0:
                        Bukkit.broadcastMessage("§a[Announcement]§f : §6Bonne chance.");
                        startGame();
                        gameStarting = false;
                        this.cancel();
                        break;
                }
                countdown--;
            }
        }.runTaskTimer(plugin, 0L, 20L); // 20L représente un délai de 1 seconde en ticks.
    }

    public void startGame() {
        // Récupérer les points de téléportation
        Set<String> teleportPoints = config.getConfigurationSection("teleport.points").getKeys(false);
        List<Location> locations = new ArrayList<>();

        for (String point : teleportPoints) {
            locations.add((Location) config.get("teleport.points." + point));
        }

        // Téléporter les joueurs de manière aléatoire
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        Collections.shuffle(players);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            Location location = locations.get(i % locations.size());
            player.teleport(location);
        }

     // Démarrer la logique du jeu
        gameLogic.startGame();

        // Mise à jour du tableau d'affichage
        updateGameScoreboard();
    }

    public void updateGameScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = gameLogic.getPlayerData(player);
            GameScoreboard gameScoreboard = new GameScoreboard();
            gameScoreboard.updateForPlayer(player, playerData);
        }
    }

}