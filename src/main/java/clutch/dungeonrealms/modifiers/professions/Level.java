package clutch.dungeonrealms.modifiers.professions;

import clutch.dungeonrealms.modifiers.Modifier;

public class Level extends Modifier {

    private int level = 0;

    public Level(int level) {
        super("level", "LEVEL");
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
