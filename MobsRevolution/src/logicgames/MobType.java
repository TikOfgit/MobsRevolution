package logicgames;

public enum MobType {
    CREEPER(0, "Creeper"),
    SPIDER(30, "Araignée"),
    IRON_GOLEM(50, "Golem de fer");

    private int cost;
    private String displayName;

    MobType(int cost, String displayName) {
        this.cost = cost;
        this.displayName = displayName;
    }

    public int getCost() {
        return cost;
    }

    public String getDisplayName() {
        return displayName;
    }
}
