package clutch.dungeonrealms.modifiers.armor.resistance;

import clutch.dungeonrealms.modifiers.Modifier;

public class FireResistance extends Modifier {

    private int fireResistance = 0;

    public FireResistance(int fireResistance) {
        super("fireResistance", "FIRE RESISTANCE");
        this.fireResistance = fireResistance;
    }

    public int getFireResistance() {
        return fireResistance;
    }
}
