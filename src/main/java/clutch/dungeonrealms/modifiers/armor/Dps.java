package clutch.dungeonrealms.modifiers.armor;

import clutch.dungeonrealms.modifiers.Modifier;

public class Dps extends Modifier {

    private int dpsMin = 0;
    private int dpsMax = 0;

    public Dps(int dpsMin, int dpsMax) {
        super("dps", "DPS");
        this.dpsMin = dpsMin;
        this.dpsMax = dpsMax;
    }

    public int getDpsMin() {
        return dpsMin;
    }

    public int getDpsMax() {
        return dpsMax;
    }
}
