package logicgames;

import org.bukkit.entity.Player;

public class PlayerData {
    private Player player;
    private MobType currentMob;
    private int points;

    public PlayerData(Player player) {
        this.player = player;
        this.currentMob = MobType.CREEPER;
        this.points = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public MobType getCurrentMob() {
        return currentMob;
    }

    public void setCurrentMob(MobType mob) {
        this.currentMob = mob;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
