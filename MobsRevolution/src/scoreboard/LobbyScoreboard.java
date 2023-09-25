package scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class LobbyScoreboard {

    private Scoreboard scoreboard;
    private Objective objective;
    private final int maxPlayers = 20;

    public LobbyScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("lobby", "dummy");
        objective.setDisplayName("§6§lMobsRevolution");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        Score space1 = objective.getScore("§1");
        space1.setScore(7);
        
        updatePlayerCount(Bukkit.getOnlinePlayers().size());
        
        Score space2 = objective.getScore("§2");
        space2.setScore(5);
        
        Score waitingScore = objective.getScore("§aEn attente de joueur");
        waitingScore.setScore(4);
        
        Score space3 = objective.getScore("§3");
        space3.setScore(3);
        
        Score websiteScore = objective.getScore("§dwww.mobsrevolution.com");
        websiteScore.setScore(1);
    }

    public void updatePlayerCount(int currentPlayers) {
        for (int i = 0; i <= maxPlayers; i++) {
            objective.getScoreboard().resetScores("§cJoueurs : §e" + i + "/" + maxPlayers);
        }
        Score playersScore = objective.getScore("§cJoueurs : §e" + currentPlayers + "/" + maxPlayers);
        playersScore.setScore(6);
    }

    public void assignToPlayer(Player player) {
        player.setScoreboard(this.scoreboard);
    }
}