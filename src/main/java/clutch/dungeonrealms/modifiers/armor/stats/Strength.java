package clutch.dungeonrealms.modifiers.armor.stats;

import clutch.dungeonrealms.modifiers.Modifier;

public class Strength extends Modifier {

    private int strength = 0;

    public Strength(int strength) {
        super("strength", "STRENGTH");
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
}
