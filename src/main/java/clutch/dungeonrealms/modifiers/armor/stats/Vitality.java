package clutch.dungeonrealms.modifiers.armor.stats;

import clutch.dungeonrealms.modifiers.Modifier;

public class Vitality extends Modifier {

    private int vitality = 0;

    public Vitality(int vitality) {
        super("vitality", "VITALITY");
        this.vitality = vitality;
    }

    public int getVitality() {
        return vitality;
    }
}
