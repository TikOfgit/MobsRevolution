package logicgames;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;

public class GameLogic {
    private Map<Player, PlayerData> playersData = new HashMap<>();

    public GameLogic() {
    }

    public PlayerData getPlayerData(Player player) {
        return playersData.get(player);
    }

    public void startGame() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playersData.put(player, new PlayerData(player));
        }
    }

    public void playerKilled(Player killer) {
        PlayerData data = playersData.get(killer);
        data.addPoints(5);

        upgradeMobIfPossible(data);
    }

    private void upgradeMobIfPossible(PlayerData data) {
        MobType currentMob = data.getCurrentMob();
        MobType nextMob = getNextMob(currentMob);

        if (nextMob != null && data.getPoints() >= nextMob.getCost()) {
            data.setCurrentMob(nextMob);
            data.getPlayer().sendMessage("Changement de votre mob en " + nextMob.getDisplayName());
        }
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