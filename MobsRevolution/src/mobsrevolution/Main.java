package mobsrevolution;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import commands.CommandAirSpawn;
import commands.CommandAirTeleport;
import commands.CommandAirDead;
import commands.CommandStart;
import listeners.PlayerJoinListener;
import listeners.PlayerQuitListener;
import listeners.PlayerKillListener;
import listeners.PowerUseListener;
import logicgames.GameLogic;
import scoreboard.LobbyScoreboard;
import scoreboard.GameScoreboard;

public class Main extends JavaPlugin {

    private LobbyScoreboard lobbyScoreboard;
    private GameLogic gameLogic;

    @Override
    public void onEnable() {
        // Configuration initiale
        saveDefaultConfig();

        // Enregistrement des commandes
        this.getCommand("airspawn").setExecutor(new CommandAirSpawn(this));
        this.getCommand("airteleport").setExecutor(new CommandAirTeleport(this));
        this.getCommand("airdead").setExecutor(new CommandAirDead(this));
        this.getCommand("start").setExecutor(new CommandStart(this));

        // Instanciation du LobbyScoreboard et enregistrement des listeners
        lobbyScoreboard = new LobbyScoreboard();
        gameLogic = new GameLogic();
        
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerKillListener(gameLogic), this);
        Bukkit.getPluginManager().registerEvents(new PowerUseListener(gameLogic), this);
    }

    @Override
    public void onDisable() {
        // TODO: Sauvegarder les points dans le fichier de configuration
    }

    public LobbyScoreboard getLobbyScoreboard() {
        return this.lobbyScoreboard;
    }

    public GameLogic getGameLogic() {
        return this.gameLogic;
    }
}