package clutch.dungeonrealms.modifiers.professions;

import clutch.dungeonrealms.modifiers.Modifier;

public class Experience extends Modifier {

    private int xp = 0;
    private int maxXP = 0;

    public Experience(int xp, int maxXP) {
        super("xp", "EXP");
        this.xp = xp;
        this.maxXP = maxXP;
    }

    public int getXp() {
        return xp;
    }

    public int getMaxXP() {
        return maxXP;
    }
}
