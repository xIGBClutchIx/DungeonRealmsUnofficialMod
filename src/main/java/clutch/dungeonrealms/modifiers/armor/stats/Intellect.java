package clutch.dungeonrealms.modifiers.armor.stats;

import clutch.dungeonrealms.modifiers.Modifier;

public class Intellect extends Modifier {

    private int intellect = 0;

    public Intellect(int intellect) {
        super("intellect", "INTELLECT");
        this.intellect = intellect;
    }

    public int getIntellect() {
        return intellect;
    }
}
