package clutch.dungeonrealms.modifiers.armor.stats;

import clutch.dungeonrealms.modifiers.Modifier;

public class Dexterity extends Modifier {

    private int dexterity = 0;

    public Dexterity(int dexterity) {
        super("dexterity", "DEXTERITY");
        this.dexterity = dexterity;
    }

    public int getDexterity() {
        return dexterity;
    }
}
