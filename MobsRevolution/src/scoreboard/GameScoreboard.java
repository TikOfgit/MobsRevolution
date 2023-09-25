package scoreboard;

import logicgames.MobType;
import logicgames.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class GameScoreboard {
    private Scoreboard scoreboard;
    private Objective objective;

    public GameScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        scoreboard = manager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("game", "dummy");
        objective.setDisplayName("§6§lMobsRevolution");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void updateForPlayer(Player player, PlayerData playerData) {
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }

        String pointsLine = "Pièce: " + playerData.getPoints();
        Score pointsScore = objective.getScore(pointsLine);
        pointsScore.setScore(5);

        Score space1 = objective.getScore(" ");
        space1.setScore(4);

        String currentMobLine = "Mobs : " + playerData.getCurrentMob().getDisplayName();
        Score currentMobScore = objective.getScore(currentMobLine);
        currentMobScore.setScore(3);

        MobType nextMob = getNextMob(playerData.getCurrentMob());
        String nextMobLine = (nextMob != null) ? "Mobs suivant : Il vous manque " + (nextMob.getCost() - playerData.getPoints()) + " Pièce" : "Mobs suivant : Max";
        Score nextMobScore = objective.getScore(nextMobLine);
        nextMobScore.setScore(2);

        Score space2 = objective.getScore("  ");
        space2.setScore(1);

        Score websiteScore = objective.getScore("§dwww.mobsrevolution.com");
        websiteScore.setScore(0);

        player.setScoreboard(this.scoreboard);
    }

    private MobType getNextMob(MobType currentMob) {
        MobType[] mobValues = MobType.values();
        int currentIndex = -1;

        for (int i = 0; i < mobValues.length; i++) {
            if (mobValues[i] == currentMob) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex + 1 < mobValues.length) {
            return mobValues[currentIndex + 1];
        } else {
            return null;
        }
    }
}