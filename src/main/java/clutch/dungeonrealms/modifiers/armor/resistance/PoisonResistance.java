package clutch.dungeonrealms.modifiers.armor.resistance;

import clutch.dungeonrealms.modifiers.Modifier;

public class PoisonResistance extends Modifier {

    private int poisonResistance = 0;

    public PoisonResistance(int poisonResistance) {
        super("poisonResistance", "POISON RESISTANCE");
        this.poisonResistance = poisonResistance;
    }

    public int getPoisonResistance() {
        return poisonResistance;
    }
}
